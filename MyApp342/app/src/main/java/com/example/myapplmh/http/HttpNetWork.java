package com.example.myapplmh.http;

import android.util.Log;

import com.example.myapplmh.json.DatapointsItem;
import com.example.myapplmh.json.DatastreamsItem;
import com.example.myapplmh.json.JsonData;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpNetWork {
    public Integer GetDataMethod(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "version=2018-10-31&res=products%2F545468&et=1700000000&method=md5&sign=ZKT4FEGucGU0fIBNxaCr%2Bw%3D%3D")
                .build();
        String responseData = "";
        Response response;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("www----------","responseData"+responseData);
        JsonData data = new Gson().fromJson(responseData, JsonData.class);
        List<DatastreamsItem> streams = data.getData().getDatastreams();
        Log.i("==========","streams:"+data);
        List<DatapointsItem> points = streams.get(0).getDatapoints();
        Log.i("www---------","points"+points);
        Integer resultData = new Integer(1);
        resultData = points.get(0).getValue();
        Log.w("www","chuanganData="+resultData);
        return resultData;
    }


    public String PostMethod(String command){
        OkHttpClient client = new OkHttpClient();
        String url = String.format("http://api.heclouds.com/v1/synccmds?device_id=999534234&timeout=30");
        String body = String.format(command);
        RequestBody bodyJson = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),body);
        Request request = new Request.Builder()
                .url(url)
                .headers(new Headers.Builder().add("Authorization",
                        "version=2018-10-31&res=products%2F545468&et=1700000000&method=md5&sign=ZKT4FEGucGU0fIBNxaCr%2Bw%3D%3D").build())
                .post(bodyJson)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("e","post请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("e","post请求成功");
            }
        });
        Response response = null;
        String responseData = "";
        try{
            response = client.newCall(request).execute();
            String jsonResStr = response.body().string();
            Log.i("OneNet回应：",jsonResStr);
            JSONObject jsonObject = new JSONObject(jsonResStr);
            responseData = jsonObject.getString("error");
            Log.i("val",responseData);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return responseData;
    }
}
