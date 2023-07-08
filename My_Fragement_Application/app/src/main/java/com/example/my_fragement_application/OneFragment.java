package com.example.my_fragement_application;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    int[] picture = {R.drawable.time, R.drawable.time, R.drawable.time, R.drawable.time, R.drawable.time};
    String[] title = {"1", "水果", "水果", "水果", "水果"};
    String[] data = {"1", "介绍", "介绍", "介绍", "介绍"};
    // 将被添加到Map映射上的键名,这里可以把键名用一个变量存着，这样就可以避免拼写错误导致拿不到
    String[] key = {"picture", "title", "data"};

    public OneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        listView = view.findViewById(R.id.lv);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(),
                R.layout.list_item_layout, key, new int[]{R.id.picture, R.id.title, R.id.data});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            // 这里实际上就是对应listview的一行数据，图片、标题、内容
            Map<String, Object> map = new HashMap<>();
            // 图片
            map.put(key[0], picture[i]);
            // 标题
            map.put(key[1], title[i]);
            // 内容
            map.put(key[2], data[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = listView.getAdapter().getItem(i).toString();
        Log.i("===================", text);
    }
}