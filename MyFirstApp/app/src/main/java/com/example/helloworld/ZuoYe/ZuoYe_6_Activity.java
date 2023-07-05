package com.example.helloworld.ZuoYe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.helloworld.AllService.MyService;
import com.example.helloworld.R;

import java.util.ArrayList;

public class ZuoYe_6_Activity extends AppCompatActivity implements View.OnClickListener{

    private Button bnt1,bnt2;
    private ImageButton img_play_stop,img_up,img_down;
    private MediaPlayer player;
    private ImageView imageView3;
    private TextView tv_time,tv1,tv2;
    private boolean isSeekBarChanging;
    private boolean isChanged = false;
    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_ye6);
        init();
    }

    public void init(){
        bnt1 = (Button) findViewById(R.id.btn1);
        bnt2 = (Button) findViewById(R.id.btn2);
        img_up = (ImageButton)findViewById(R.id.img_up);
        img_play_stop = (ImageButton)findViewById(R.id.img_play_stop);
        img_down = (ImageButton)findViewById(R.id.img_down);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        tv_time = (TextView) findViewById(R.id.time);
        tv1 = (TextView) findViewById(R.id.text_test1);
        tv2 = (TextView) findViewById(R.id.text_test2);

        SeekBar seekBar = (SeekBar) findViewById(R.id.playSeekBar);

        bnt1.setOnClickListener(this);
        bnt2.setOnClickListener(this);
        img_up.setOnClickListener(this);
        img_play_stop.setOnClickListener(this);
        img_down.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new MySeekBar());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()){
            case R.id.img_play_stop:
            if (isChanged){
                img_play_stop.setImageResource(R.drawable.play);
                stopService(intent);
                imageView3.clearAnimation();
            }else {
                img_play_stop.setImageResource(R.drawable.stop);
                startService(intent);
                Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotaterepeat);
                LinearInterpolator lin = new LinearInterpolator();
                animation.setInterpolator(lin);
                imageView3.startAnimation(animation);
            }
            isChanged = !isChanged;
            break;

            case R.id.img_up:

            case R.id.img_down:
                break;

        }
    }

    private class MySeekBar implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tv1.setText("正在拖动");
            
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
            Log.i("message","start");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            player.seekTo(seekBar.getProgress());
            Log.i("massage","stop");
            
        }
    }

}