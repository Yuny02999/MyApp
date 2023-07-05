package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Call_Activity extends AppCompatActivity{
    ImageButton HangUp,speaker,mike_open;
    Chronometer timer;
    TextView speaker_data,stopCall_Text,mike_open_data;
    ImageButton minSize;
    private boolean mike_change;
    private boolean speaker_change;
    long recordingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        mike_open = findViewById(R.id.mike_open);
        HangUp = findViewById(R.id.HangUp);
        speaker = findViewById(R.id.speaker);
        timer =  findViewById(R.id.timer);
        mike_open_data = findViewById(R.id.mike_open_data);
        speaker_data = findViewById(R.id.speaker_data);
        minSize = findViewById(R.id.minSize);
        stopCall_Text = (TextView) findViewById(R.id.stopCall_Text);
        GoTimer();

        mike_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mike_change){
                    mike_open.setImageResource(R.drawable.open_voice);
                    mike_open_data.setText("麦克风开启");
                }else {
                    mike_open.setImageResource(R.drawable.close_voice);
                    mike_open_data.setText("麦克风关闭");
                }mike_change =! mike_change;
            }
        });

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (speaker_change){
                    speaker.setImageResource(R.drawable.open_);
                    speaker_data.setText("扬声器开启");
                }else {
                    speaker.setImageResource(R.drawable.close_);
                    speaker_data.setText("扬声器关闭");
                }speaker_change =! speaker_change;
            }
        });

        HangUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCall_Text.setText("通话结束");
                timer.stop();
                recordingTime = SystemClock.elapsedRealtime()-timer.getBase();
                Log.i("本次通话时长为", String.valueOf(recordingTime));
                
                finish();
            }
        });

        minSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                Log.i("点击了moveTaskToBack","true");
            }
        });
    }

    private void GoTimer() {
        timer.setBase(SystemClock.elapsedRealtime()-recordingTime);
        timer.start();//开始计时
    }
}