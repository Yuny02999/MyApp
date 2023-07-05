package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;

public class ZhuCeActivity extends AppCompatActivity {

    private Button button_register;
    private EditText username, password, password_;
    private String info = "user-password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        initView();


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置内容只能为数字和英文
                username.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_TEXT);

                SharedPreferences sharedPreferences = getSharedPreferences(info,0);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                String UserName = username.getText().toString();
                String PassWord = password.getText().toString();
                String PassWord_ = password_.getText().toString();
                if (TextUtils.isEmpty(UserName)) {
                    Toast.makeText(ZhuCeActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(PassWord)) {
                    Toast.makeText(ZhuCeActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(PassWord_)) {
                    Toast.makeText(ZhuCeActivity.this, "二次密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (PassWord.equals(PassWord_)){
                    int i = sharedPreferences.getInt("i",0);
                    i++;

                    SharedPreferences userSp = getSharedPreferences(info,0);

                    editor.putInt("i",i);
                    editor.putString("UserName",UserName);
                    editor.putString("PassWord",PassWord);
                    editor.apply();

                    Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ZhuCeActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    password_.setText("");
                }
            }
        });

    }

    private void initView() {
        button_register = findViewById(R.id.button_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password3);
        password_ = findViewById(R.id.password_);
    }
}
