package com.example.helloworld.TaoBao.Seek_status_bar.Fragment_Homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;



public class Fragment_Recommend extends Fragment {
    RecyclerView Rec1;
    public Fragment_Recommend() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__recommend, container, false);


    }

}