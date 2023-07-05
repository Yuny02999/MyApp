package com.example.helloworld.ZuoYe;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZuoYe_4_Activity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_zuo_ye4);

        SimpleAdapter adapter = new SimpleAdapter(this,getData(),
                R.layout.activity_zuo_ye4,
                new String[]{"title","info","picture"},
                new int[]{R.id.title,R.id.info,R.id.picture});
        setListAdapter(adapter);
    }

    private List<Map<String ,Object>>getData() {
        List<Map<String , Object>>list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("title","蓝天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","绿天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","晴天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","黄天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","彩天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","绿天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","晴天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","黄天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","彩天白云");
        map.put("info","蓝天白云.......");
        map.put("picture",R.drawable.nohomework);
        list.add(map);

        return list;
    }


}