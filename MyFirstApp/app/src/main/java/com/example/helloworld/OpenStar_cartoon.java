package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class OpenStar_cartoon extends AppCompatActivity {
    private ImageView wrap = null;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_star_cartoon);
        initView();

        AlphaAnimation animation = new AlphaAnimation(1.0f,1.0f);
        animation.setDuration(2500);
        wrap.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                wrap.setBackgroundResource(R.drawable.star_picture1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                skip();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });

    }

    private void initView() {
        btn1 = findViewById(R.id.btn1);
        wrap = findViewById(R.id.wrap);
    }


    private void skip() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}