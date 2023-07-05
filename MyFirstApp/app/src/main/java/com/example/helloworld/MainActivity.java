package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.*;


import com.example.helloworld.TaoBao.TaoBao_MainActivity;
import com.example.helloworld.ZuoYe.ZuoYe_1_Activicty;
import com.example.helloworld.ZuoYe.ZuoYe_2_Activity;
import com.example.helloworld.ZuoYe.ZuoYe_3_Activity;
import com.example.helloworld.ZuoYe.ZuoYe_4_Activity;
import com.example.helloworld.ZuoYe.ZuoYe_5_Activity;
import com.example.helloworld.ZuoYe.ZuoYe_6_Activity;
import com.example.helloworld.ZuoYe.ZuoYe_7_Activity;
import com.superluo.textbannerlibrary.TextBannerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button denglu1, zhuce, homework1, GDCP,
            homework2, homework3, homework4, homework5, homework6,
            homework7, Sql, bigUp, Call, Seek, Spinner1, PhotoAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        makeBackgroundAll();

        //轮播图
//            ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
//            flipper.startFlipping();
//            flipper.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlphaAnimation animation = (AlphaAnimation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);
//                    flipper.startAnimation(animation);
//                }
//            });

        //跑马灯
//            TextView tv = findViewById(R.id.tv);
//            tv.requestFocus();


        //公告
        TextBannerView textBanner = findViewById(R.id.textBanner);
        List<String> tv_Ban = new ArrayList<>();
        for (int a = 0; a < 10; a++) {
            tv_Ban.add("这是一条公告" + a);
            //设置公告点击事件
            textBanner.setItemOnClickListener((data, position) -> Toast.makeText(MainActivity.this, "" + data, Toast.LENGTH_SHORT).show());

        }
        textBanner.setDatas(tv_Ban);


        PhotoAlbum.setOnClickListener(view -> {
            askPermission();
            Intent intent1 = new Intent();
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            intent1.addCategory(Intent.CATEGORY_OPENABLE);
            intent1.setType("image/*");
        });


        Spinner1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Spinner_Test.class);
            startActivity(intent);
            vibrator();
        });


        Seek.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaoBao_MainActivity.class);
            startActivity(intent);
            vibrator();
        });

        Call.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Call_Activity.class);
            startActivity(intent);
            vibrator();
        });

        bigUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Data_BigUp_Activity.class);
            startActivity(intent);
            vibrator();
        });

        Sql.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SQLite_Activity.class);
            startActivity(intent);
            vibrator();
        });

        homework7.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ZuoYe_7_Activity.class);
            startActivity(intent);
            vibrator();
        });

        homework6.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ZuoYe_6_Activity.class);
            startActivity(intent);
            vibrator();
        });

        homework5.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ZuoYe_5_Activity.class);
            startActivity(intent);
            vibrator();
        });


        homework4.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ZuoYe_4_Activity.class);
            startActivity(intent);
            vibrator();
        });


        homework3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ZuoYe_3_Activity.class);
            startActivity(intent);
            vibrator();
        });


        homework1.setOnClickListener(view -> {
            Intent h = new Intent(MainActivity.this, ZuoYe_1_Activicty.class);
            startActivity(h);
            vibrator();
        });

        zhuce.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ZhuCeActivity.class);
            startActivity(i);
            vibrator();
        });


        denglu1.setOnClickListener(view -> {
            Intent d = new Intent(MainActivity.this, dengluMain.class);
            startActivity(d);
            vibrator();
        });

        GDCP.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "跳转成功", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("http://jw.gdcp.cn/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            vibrator();
        });

        homework2.setOnClickListener(view -> {
            vibrator();
            Intent d = new Intent(MainActivity.this, ZuoYe_2_Activity.class);
            startActivity(d);
        });
    }

    private void vibrator() {
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{0, 20}, -1);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void makeBackgroundAll() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            //getWindow.setStatusBarColor(Color.TRANSPARENT)
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    private void initView() {
        denglu1 = findViewById(R.id.denglu1);
        zhuce = findViewById(R.id.zhuce);
        homework1 = findViewById(R.id.homework1);
        GDCP = findViewById(R.id.GDCP);
        homework2 = findViewById(R.id.homework2);
        homework3 = findViewById(R.id.homework3);
        homework4 = findViewById(R.id.homework4);
        homework5 = findViewById(R.id.homework5);
        homework6 = findViewById(R.id.homework6);
        homework7 = findViewById(R.id.homework7);
        Sql = findViewById(R.id.Sql);
        bigUp = findViewById(R.id.bigUp);
        Call = findViewById(R.id.Call);
        Seek = findViewById(R.id.Seek);
        Spinner1 = findViewById(R.id.Spinner1);
        PhotoAlbum = findViewById(R.id.PhotoAlbum);
    }


    private void askPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    Log.i("permissions", str);
                }
            }
        }
    }


    public void onBackPressed() {
        AlertDialog dialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("对话框")
                .setIcon(R.drawable.apppc)
                .setMessage("是否退出应用:")
                .setPositiveButton("确定", (dialog1, i) -> {
                    dialog1.dismiss();
                    MainActivity.this.finish();
                })
                .setNegativeButton("取消", (dialog12, i) -> dialog12.dismiss());
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_style);
        dialog.show();
    }
}
