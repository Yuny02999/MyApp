package com.example.helloworld.ZuoYe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;

public class ZuoYe_3_Activity extends AppCompatActivity {

    private Button Button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_ye3);

        Button1 = findViewById(R.id.Botton1);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String[] items = new String[]{"北京", "上海", "广州", "深圳"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ZuoYe_3_Activity.this);
                builder.setIcon(R.mipmap.ic_launcher).setTitle("请选择：")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ZuoYe_3_Activity.this, items[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.create().show();

            }
        });


    }
}