package com.example.helloworld.ZuoYe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helloworld.R;
import com.example.helloworld.secondActivity;

public class ZuoYe_5_Activity extends AppCompatActivity {

    private Button bnt1;
    private Button bnt2;
    private Button bnt3;
    private Button bnt4;
    private Button bnt5;
    private EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_ye5);

        bnt1 = (Button) findViewById(R.id.button1);
        bnt2 = (Button) findViewById(R.id.button2);
        bnt3 = (Button) findViewById(R.id.button3);
        bnt4 = (Button) findViewById(R.id.button4);
        bnt5 = (Button) findViewById(R.id.button5);
        edt1 = (EditText)findViewById(R.id.edt1);

        bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ZuoYe_5_Activity.this, secondActivity.class);
                intent.putExtra("name","李四");
                intent.putExtra("sex","男");
                intent.putExtra("Tel","110");
                startActivity(intent);
            }
        });

        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.putExtra("sms_body" , "你好，这是测试短信，勿回");
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
            }
        });

        bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + edt1.getText()));
                startActivity(intent);
            }
        });

        bnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telstr = edt1.getText().toString();
                Uri uri = Uri.parse("tel" + telstr);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        bnt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.gdcp.cn");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

    }
}