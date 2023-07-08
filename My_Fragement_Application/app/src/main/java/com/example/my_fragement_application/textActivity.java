package com.example.my_fragement_application;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class textActivity extends AppCompatActivity {
    List<Map<String, Object>> list = new ArrayList<>();

    int[] picture = {R.drawable.time};
    String[] title = {"张三", "李四", "王五", "李六"};
    String[] data = {"内容", "内容", "内容", "内容"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        ListView list = findViewById(R.id.list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
                R.layout.list_item_layout,
                new String[]{"picture", "title", "data"},
                new int[]{R.id.picture, R.id.title, R.id.data});
        list.setAdapter(simpleAdapter);
    }

    private List<Map<String, Object>> getData() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < title.length; i++) {
            map.put("picture", picture);
            map.put("title", title);
            map.put("data", data[i]);
        }
        return list;
    }
}