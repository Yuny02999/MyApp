package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    ImageView more;
    TextView tv1, tv2;
    private boolean flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        tv1.append("123");

    }

    private void initView() {
        more = findViewById(R.id.more);
        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.more) {
            if (!flg) {
                flg = true;
                @SuppressLint("Recycle") ObjectAnimator animator = ObjectAnimator.ofFloat(more, "rotation", 0f, 180f);
                animator.setDuration(300).start();
            } else {
                flg = false;
                @SuppressLint("Recycle") ObjectAnimator animator = ObjectAnimator.ofFloat(more, "rotation", 180f, 0f);
                animator.setDuration(300).start();
            }
        }

        if (view.getId() == R.id.text1){

        }
        if (view.getId() == R.id.text2){

        }
    }
}