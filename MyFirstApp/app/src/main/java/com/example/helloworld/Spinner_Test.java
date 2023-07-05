package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.helloworld.TaoBao.TaoBao_MainActivity;

import java.util.ArrayList;

public class Spinner_Test extends AppCompatActivity {
    Spinner spinner1;
    TextView return1,spinner_text;
    ViewPager vPager1;
    private TaoBao_MainActivity.MyPagerAdapter currAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);
        initView();

        ArrayList<String> list = new ArrayList<String>();
        list.add("1~7周"); //初始显示
        list.add("第八周");
        list.add("9~11周");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setSelection(0);


        ArrayList<View> list1 = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        list1.add(inflater.inflate(R.layout.one_curriculum,null,false));
        list1.add(inflater.inflate(R.layout.two_curriculum,null,false));
        list1.add(inflater.inflate(R.layout.three_curriculum,null,false));
        currAdapter = new TaoBao_MainActivity.MyPagerAdapter(list1);
        vPager1.setAdapter(currAdapter);
        vPager1.setCurrentItem(0);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    vPager1.setCurrentItem(0,true);
                }else if(i == 1){
                    vPager1.setCurrentItem(1,true);
                }else if(i == 2){
                    vPager1.setCurrentItem(2,true);
                }else if(i == 3){
                    vPager1.setCurrentItem(3,true);
                }else{
                    vPager1.setCurrentItem(4,true);
                }


                vPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        ChangeText(position);
                    }



                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
            private void ChangeText(int position) {
                if (position == 0){
                    spinner_text.setText("当前在1~7周");
                }else if(position == 1){
                    spinner_text.setText("当前在第八周");
                }else {
                    spinner_text.setText("当前在9~11周");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        return1 = (TextView)findViewById(R.id.return1);
        vPager1 = (ViewPager) findViewById(R.id.vPager1);
        spinner_text = (TextView)findViewById(R.id.spinner_text);
    }


}