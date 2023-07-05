package com.example.socketactivity;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ClientInfoStatus;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Button sendmsgbt,StartSeverBtn;
    private EditText sendMsged,friendID;
    private TextView Show_Text,showmsg;
    private Handler handler;
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;
    private String receiveTxt,SendMsg;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.INTERNET
    };
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applypermission();
        initView();


        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                if (msg.what == 1){
                    showmsg.append(receiveTxt);
                }else if (msg.what == 2){
                    showmsg.append("服务器:"+sendMsged.getText()+"\n");
                    sendMsged.setText("");
                }
            }
        };

        sendmsgbt.setOnClickListener(view -> {
            String sendEd = sendMsged.getText().toString();
            if (TextUtils.isEmpty(sendEd)){
                Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            SendMsg = sendMsged.getText().toString();
        });



        StartSeverBtn.setOnClickListener(view -> {
            ServerThread.start();
            SendMsgThread.start();
            Show_Text.setText("开启连接");

        });

    }
    Thread ServerThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                server = new ServerSocket(Integer.parseInt(friendID.getText().toString()));
                socket = server.accept();
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    receiveTxt = in.readUTF() + "\n";
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    Thread SendMsgThread = new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while(true){
                        if(SendMsg != ""){
                            try {
                                out.writeUTF("服务器:"+SendMsg);
                                handler.sendEmptyMessage(2);
                                SendMsg = "";
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
        sendmsgbt = findViewById(R.id.sendmsgbt);
        friendID = findViewById(R.id.friendID);
        Show_Text = findViewById(R.id.Show_Text);
        showmsg = findViewById(R.id.showmsg);
        sendMsged = findViewById(R.id.sendMsged);
        StartSeverBtn = findViewById(R.id.StartSeverBtn);
    }
}