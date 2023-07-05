package com.example.helloworld.AllService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.helloworld.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    MediaPlayer player = new MediaPlayer();
    private Timer timer;

    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("MyService", "已启用");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        player = MediaPlayer.create(this, R.raw.music0);
        player.start();
        Log.i("MyService","已开始");
        Toast.makeText(this,"开始播放",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startID);
    }


    @Override
    public void onDestroy(){
        player.stop();
        Toast.makeText(this,"结束播放",Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.i("MyService","已关闭");
    }


}
