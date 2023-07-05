package com.example.helloworld.ZuoYe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ZuoYe_7_Activity extends AppCompatActivity implements View.OnClickListener{
    Button btn_request;
    TextView txt_show;
    String urlString="http://124.93.196.45:10001/prod-api/api/metro/notice/1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_zuo_ye7);
        btn_request = ( Button ) findViewById( R.id.btn_request );
        btn_request.setOnClickListener( this );
        txt_show = (TextView) findViewById(R.id.txt_show);
        WebView web1 = (WebView) findViewById(R.id.web1);
        web1.loadUrl("https://www.csdn.net/");
        web1.setWebViewClient(new WebViewClient());
        web1.getSettings().setSupportZoom(true);
        web1.getSettings().setBuiltInZoomControls(true);

    }
    public void onClick(View v) {
        if (v.getId() == R.id.btn_request) {
            sendRequestWithHttpURLConnection();
        }
    }


    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(8000);//设置传递数据超时时间
                    connection.setConnectTimeout(8000);//设置超时时间
                    connection.setRequestMethod("GET");//设置请求方式

                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }

    //数据显示在textView中
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_show.setText( response );
            }
        });
    }


}
