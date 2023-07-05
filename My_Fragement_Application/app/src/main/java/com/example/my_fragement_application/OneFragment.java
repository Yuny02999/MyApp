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

    public OneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        listView = view.findViewById(R.id.lv);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(),
                R.layout.list_item_layout,
                new String[]{"picture","title", "data"},
                new int[]{R.id.picture,R.id.title, R.id.data});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String[] title = {"1", "水果", "水果", "水果", "水果"};
            String[] data = {"1", "介绍", "介绍", "介绍", "介绍"};
            int[] picture = {R.drawable.time};
            Map<String, Object> map = new HashMap<>();
            map.put("picture",picture);
            map.put("title", title);
            map.put("data", data);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = listView.getAdapter().getItem(i).toString();
        Log.i("===================",text);
    }
}