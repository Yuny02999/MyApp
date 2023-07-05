package com.example.socketseveractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Button StartSeverBtn,sendmsgbt;
    private TextView showmsg,Show_Text;
    private EditText ipv4ed,sendMsged,ported;
    private Handler handler;
    private String receiveTxt,SendMsg = "";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.INTERNET
    };
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        applypermission();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                if (msg.what==1){
                    showmsg.append(receiveTxt);
                }else if (msg.what==2){
                    showmsg.append("客户端:"+sendMsged.getText()+"\n");
                    sendMsged.setText("");
                }
            }
        };

        StartSeverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipv4Text = ipv4ed.getText().toString();
                String portText =ported.getText().toString();
                if (TextUtils.isEmpty(ipv4Text)){
                    Toast.makeText(MainActivity.this,"请输入ipv4",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(portText)){
                    Toast.makeText(MainActivity.this, "请输入port", Toast.LENGTH_SHORT).show();
                    return;
                }
                connectionSeverThread.start();
                SendMsgThread.start();
                Show_Text.setText("连接成功");
            }
        });

        sendmsgbt.setOnClickListener(view -> {
            String sendEd = sendMsged.getText().toString();
            if (TextUtils.isEmpty(sendEd)){
                Toast.makeText(MainActivity.this,"内容不能为空",Toast.LENGTH_LONG).show();
                return;
            }
            handler.sendEmptyMessage(24);
            SendMsg = sendMsged.getText().toString();
        });
    }


    Thread connectionSeverThread =new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                socket =new Socket(ipv4ed.getText().toString(),Integer.parseInt(ported.getText().toString()));
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){
                try {
                    Log.i("============","ported="+ported);
                    Log.i("============","ipv4ed="+ipv4ed);
                    Log.i("============","socket="+socket);
                    Log.i("============","in="+in);
                    receiveTxt = in.readUTF()+"\n";
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    Thread SendMsgThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                if (SendMsg != ""){
                    try {
                        out.writeUTF("客户端:"+SendMsg);
                        handler.sendEmptyMessage(2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SendMsg = "";
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private void applypermission() {
        if(Build.VERSION.SDK_INT>=23){
            boolean needapply=false;
            for(int i=0;i<PERMISSIONS_STORAGE.length;i++){
                int chechpermission= ContextCompat.checkSelfPermission(getApplicationContext(),
                        PERMISSIONS_STORAGE[i]);
                if(chechpermission!= PackageManager.PERMISSION_GRANTED){
                    needapply=true;
                }
            }
            if(needapply){
                ActivityCompat.requestPermissions(MainActivity.this,PERMISSIONS_STORAGE,1);
            }
        }
    }

    private void initView() {
        StartSeverBtn = findViewById(R.id.StartSeverBtn);
        sendmsgbt = findViewById(R.id.sendmsgbt);
        showmsg = findViewById(R.id.showmsg);
        Show_Text = findViewById(R.id.Show_Text);
        ipv4ed = findViewById(R.id.ipv4ed);
        sendMsged = findViewById(R.id.sendMsged);
        ported = findViewById(R.id.ported);
    }
}