package com.example.myapplmh;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplmh.draw.DynamicLineChartManager;
import com.example.myapplmh.http.HttpNetWork;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LineChart soilLightChar,temHumChar;
    Button btnStart,btnStop,open_close_LED;
    TextView textViewTH, textViewSL;
    Thread thread;
    String urlTem="http://api.heclouds.com/devices/999534234/datapoints?datastream_id=temperature&limit=3";
    String urlHum="http://api.heclouds.com/devices/999534234/datapoints?datastream_id=humidity&limit=3";
    String urlSoil="http://api.heclouds.com/devices/999534234/datapoints?datastream_id=soil&limit=3";
    String urlLight="http://api.heclouds.com/devices/999534234/datapoints?datastream_id=light&limit=3";
    HttpNetWork httpNetWork = new HttpNetWork();

    private List<Float> listTemHum = new ArrayList<>();
    private List<String> namesTemHum = new ArrayList<>();
    private List<Integer> colorTemHum = new ArrayList<>();
    private List<Float> listSoilLigth = new ArrayList<>();
    private List<String> namesSoilLight = new ArrayList<>();
    private List<Integer> colorSoilLight = new ArrayList<>();
    private DynamicLineChartManager dynamicTemHum,dynamicSoilLigth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        open_close_LED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (open_close_LED.getText()=="关灯"){
                    open_close_LED.setText("开灯");
                    new Thread(() -> {
                        Looper.prepare();
                        String openRes = httpNetWork.PostMethod("1");
                        Toast.makeText(getApplicationContext(),openRes.toString(),Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }).start();
                }
                else {
                    open_close_LED.setText("关灯");
                    new Thread(() -> {
                        Looper.prepare();
                        String stopRes = httpNetWork.PostMethod("0");
                        Toast.makeText(getApplicationContext(),stopRes.toString(),Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }).start();
                }
            }
        });

        btnStart.setOnClickListener(view -> {
            thread = new Thread(() -> {
                while (!thread.isInterrupted()){
                    try {
                        Float temp = httpNetWork.GetDataMethod(urlTem).floatValue();
                        Float hum = httpNetWork.GetDataMethod(urlHum).floatValue();
                        Float soilTem = httpNetWork.GetDataMethod(urlSoil).floatValue();
                        Float light = httpNetWork.GetDataMethod(urlLight).floatValue();

                        textViewTH.post(new Runnable() {
                            @Override
                            public void run() {
                                textViewTH.setText("温度："+temp+"湿度："+hum);
                                textViewSL.setText("土壤温度："+soilTem+"光照"+light);
                                listTemHum.add(temp);
                                listTemHum.add(hum);
                                listSoilLigth.add(soilTem);
                                listSoilLigth.add(light);
                                //将数据加入折线图中
                                dynamicTemHum.addEntry(listTemHum);
                                dynamicSoilLigth.addEntry(listSoilLigth);
                                listTemHum.clear();
                                listSoilLigth.clear();
                            }
                        });
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
            thread.start();

        });
        btnStop.setOnClickListener(view -> {
            thread.interrupt();
            Log.i("eee","线程中断！！！");
        });
    }
    public void initView(){
        temHumChar = findViewById(R.id.temhum_chart_data);
        soilLightChar = findViewById(R.id.soillight_chart_data);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        open_close_LED = findViewById(R.id.open_close_LED);
        btnStart.setBackgroundColor(Color.TRANSPARENT);
        btnStop.setBackgroundColor(Color.TRANSPARENT);
        textViewTH = findViewById(R.id.txtShow);
        textViewSL = findViewById(R.id.txtShowSL);
    }

    public void initData(){
        namesTemHum.add("温度");
        namesTemHum.add("湿度");

        colorTemHum.add(Color.RED);
        colorTemHum.add(Color.BLUE);

        namesSoilLight.add("土壤光度");
        namesSoilLight.add("光照");

        colorSoilLight.add(Color.YELLOW);
        colorSoilLight.add(Color.GREEN);

        dynamicTemHum = new DynamicLineChartManager(temHumChar,namesTemHum,colorSoilLight);
        dynamicSoilLigth = new DynamicLineChartManager(soilLightChar,namesSoilLight,colorSoilLight);
        dynamicTemHum.SetDescription("温度与湿度");
        dynamicSoilLigth.SetDescription("土壤温度与光照");
        dynamicTemHum.setYAxis(100,0,10);
        dynamicSoilLigth.setYAxis(4095,0,100);
    }
}