package com.example.mycounter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageButton;
    TextView textView, change_time, title_sum;
    Chronometer timer;
    long recordingTime;
    ConstraintLayout bigLayout;
    Button minus, list_button, change_add;
    int sum = 0;
    int addSum = 1;
    int stopTime = 10000;
    AlertDialog dialog;
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        TimeGo();
        getHistoryData();
//        startAutoRecord();

        textView.setText(String.valueOf(sum));
        @SuppressLint("InflateParams") View view_new = LayoutInflater.from(this)
                .inflate(R.layout.timer_layout, null);
        timer.setOnChronometerTickListener(chronometer -> {
            EditText time = view_new.findViewById(R.id.change_edt1);
            String time_data = time.getText().toString();
            if (!time_data.isEmpty()) {
                stopTime = Integer.parseInt(time.getText().toString());
            }
            if (SystemClock.elapsedRealtime() - chronometer.getBase() >= stopTime) {
                timer.stop();
                //开启震动
                doVibrator();
            }
        });

        //textView长按监听
        textView.setOnLongClickListener(view -> {
            dialog = new AlertDialog.Builder(MainActivity.this).create();
            dialog.show();
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
            @SuppressLint("InflateParams") View view1 = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.change_layout, null);
            window.setContentView(view1);
            final EditText editText = view1.findViewById(R.id.change_edt1);
            final Button bnt = view1.findViewById(R.id.ok);
            bnt.setOnClickListener(view2 -> {
                String editTemp = editText.getText().toString();
                textView.setText(editTemp);
                sum = Integer.parseInt(editTemp);
                dialog.dismiss();
            });
            return false;
        });
    }

    //震动
    private void doVibrator() {
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{0, 100}, -1);
    }

    private void TimeGo() {
        timer.setBase(SystemClock.elapsedRealtime() - recordingTime);
        timer.start();
    }

    //打开时自动获取上次次数赋予textView
    private void getHistoryData() {
        SharedPreferences sp = getSharedPreferences("frequency", 0);
        String data = sp.getString("key", "");
        if (!data.equals("")) {
            sum = Integer.parseInt(data);
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("key", "0");
            editor.apply();
        }
    }

    private void initView() {
        imageButton = findViewById(R.id.imageButton);
        change_time = findViewById(R.id.change_time);
        timer = findViewById(R.id.timer);
        textView = findViewById(R.id.tv);
        minus = findViewById(R.id.minus);
        list_button = findViewById(R.id.list_button);
        title_sum = findViewById(R.id.title_sum);
        bigLayout = findViewById(R.id.bigLayout);
        change_add = findViewById(R.id.change_add);

        imageButton.setOnClickListener(this);
        change_time.setOnClickListener(this);
        timer.setOnClickListener(this);
        bigLayout.setOnClickListener(this);
        textView.setOnClickListener(this);
        minus.setOnClickListener(this);
        list_button.setOnClickListener(this);
        title_sum.setOnClickListener(this);
        change_add.setOnClickListener(this);

    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_add:
                changeAddSum();
                break;
            case R.id.change_time:
                changeStopTime();
                break;
            case R.id.bigLayout:
                sum = sum + addSum;
                textView.setText(String.valueOf(sum));
//                vibrator.cancel();
                recordingTime = 0;
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                startAutoRecord_text();
                break;
            case R.id.minus:
                sum = sum - 1;
                textView.setText(String.valueOf(sum));
                break;
            case R.id.list_button:
//                historyAdd();
                Intent intent = new Intent(this, BooksActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton:
                Snackbar.make(bigLayout, "==test==", Snackbar.LENGTH_SHORT)
                        .setAction("关闭", view1 -> Log.i("==", "==")).show();
                break;
        }
    }

    private void startAutoRecord_text() {
        SharedPreferences sp2 = getSharedPreferences("frequency", 0);
        SharedPreferences.Editor editor = sp2.edit();
        String data = textView.getText().toString();
        editor.putString("key", data);
        editor.apply();
        Log.i("================","=================");
    }

    private void changeAddSum() {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.sum_layout,null);
        dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setContentView(view);
        final Button button = view.findViewById(R.id.ok);
        final EditText editText = view.findViewById(R.id.change_edt1);
        button.setOnClickListener(v -> {
            String data = editText.getText().toString();
            if (data.isEmpty()){
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            }else {
                addSum = Integer.parseInt(data);
                dialog.dismiss();
            }
        });
    }

    private void changeStopTime() {
        @SuppressLint("InflateParams") View view_new = LayoutInflater.from(this)
                .inflate(R.layout.timer_layout, null);
        dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window1 = dialog.getWindow();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        window1.setAttributes(lp);
        window1.setContentView(view_new);
        final Button button = view_new.findViewById(R.id.ok);
        final EditText editText = view_new.findViewById(R.id.change_edt1);
        button.setOnClickListener(view -> {
            recordingTime = 0;
            timer.setBase(SystemClock.elapsedRealtime());
            stopTime = Integer.parseInt(editText.getText().toString()) * 1000;
            dialog.dismiss();
            timer.start();
        });
    }
}