package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;


public class dengluMain extends AppCompatActivity {

    private EditText userText;
    private EditText passwordText;
    private Button DLqueding,DLzhuce;
    private ImageButton iB1;
    private String info = "user_info";
    public ToggleButton eyes;
    private Button DLyanzheng;
    private Button Delete;
    private TextView txv1;
    private CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_main);

        initView();

        txv1.setOnClickListener(view -> Snackbar.make(view, "没有账号？", Snackbar.LENGTH_SHORT).setAction("点击跳转", v -> {
            Toast.makeText(dengluMain.this, "点击了跳转", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(dengluMain.this, ZhuCeActivity.class);
            startActivity(intent);
        }).show());

        DLzhuce.setOnClickListener(view -> {
            Intent intent = new Intent(dengluMain.this, ZhuCeActivity.class);
            startActivity(intent);
        });


        eyes.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eyes.setBackgroundResource(R.drawable.close_eyes);
            } else {
                passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eyes.setBackgroundResource(R.drawable.open_eyes);
            }
        });

        DLqueding.setOnClickListener(view -> {
            String user = userText.getText().toString();
            String password = passwordText.getText().toString();
            if (TextUtils.isEmpty(user)) {
                Toast.makeText(dengluMain.this, "请输入账号!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(dengluMain.this, "请输入密码!", Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences sp = getSharedPreferences(info, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            int i = sp.getInt("i", 0);
            i++;

            String NewUser = "user" + i;
            String NewPassword = "password" + i;

            editor.putInt("i", i);
            editor.putString(NewUser, user);
            editor.putString(NewPassword, password);
            editor.apply();


            Toast.makeText(dengluMain.this, "登录成功", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(dengluMain.this).setTitle("登录信息")
                    .setMessage("用户" + sp.getString("", user) + "登录成功")
                    .setPositiveButton("确定", (dialogInterface, i1) -> {
                        Intent intent = new Intent(dengluMain.this, ZhuJieMianMain.class);
                        startActivity(intent);
                    }).show();
        });

        DLyanzheng.setOnClickListener(view -> {
            SharedPreferences sp1 = getSharedPreferences(info, 0);
            SharedPreferences.Editor editor1 = sp1.edit();
            int i = sp1.getInt("i", 0);
            String UNamei = new String(String.valueOf(i));
            String UPasswordi = new String(String.valueOf(i));
            String UName = sp1.getString("user" + UNamei, "");
            String UPassword = sp1.getString("password" + UPasswordi, "");
            i++;


            final String[] items1 = new String[]{"user:" + UName + "," + "pass:" + UPassword};

            if (remember.isChecked()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dengluMain.this);
                builder.setIcon(R.mipmap.ic_launcher).setTitle("请选择需要的数据：")
                        .setSingleChoiceItems(items1, 0, (dialog, which) -> {
                            Toast.makeText(dengluMain.this, items1[which], Toast.LENGTH_SHORT).show();
                            userText.setText(UName);
                            passwordText.setText(UPassword);

                        });
                builder.create().show();
            } else {
                Toast.makeText(dengluMain.this, "请先开启历史", Toast.LENGTH_LONG).show();
            }
            editor1.apply();
        });


        iB1.setOnClickListener(view -> finish());

        Delete.setOnClickListener(view -> {
            SharedPreferences sp2 = getSharedPreferences(info, 0);
            SharedPreferences.Editor editor = sp2.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(dengluMain.this, "删除成功！", Toast.LENGTH_SHORT).show();
        });

    }

    private void initView() {
        remember = findViewById(R.id.checkbox2);
        DLqueding = findViewById(R.id.DLqueding);
        DLzhuce = findViewById(R.id.DLzhuce);
        iB1 = findViewById(R.id.iB1);
        userText = findViewById(R.id.zh);
        passwordText = findViewById(R.id.mm);
        eyes = findViewById(R.id.eyes);
        DLyanzheng = findViewById(R.id.DLyanzheng);
        Delete = findViewById(R.id.Delete);
        txv1 = findViewById(R.id.textView1);
    }

}


//            DLqueding.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String name = userText.getText().toString();
//                String password = passwordText.getText().toString();
//                if(TextUtils.isEmpty(name)){
//                    Toast.makeText(dengluMain.this,"请输入用户名!",Toast.LENGTH_SHORT).show();;
//                    return;
//                }
//                if (TextUtils.isEmpty(password)){
//                    Toast.makeText(dengluMain.this,"请输入密码!",Toast.LENGTH_SHORT).show();;
//                    return;
//                }
//
//
//                if (name.equals("123456")){
//                    if (password.equals("654321")){
//                        Toast.makeText(dengluMain.this,"登录成功！",Toast.LENGTH_SHORT).show();
//                        Intent z = new Intent(dengluMain.this,ZhuJieMianMain.class);
//                        startActivity(z);
//                    }else {
//                        Toast.makeText(dengluMain.this,"密码错误！",Toast.LENGTH_SHORT).show();
//                    }
//
//                }else {
//                    Toast.makeText(dengluMain.this,"账号错误！",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
