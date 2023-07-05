package com.example.my_fragement_application;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title, weixin, tongxunlu, faxian, me;
    private ViewPager vp;
    private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
    String[] titles = new String[]{"微信", "通讯录", "发现", "我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initViews();

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);

        vp.setOffscreenPageLimit(4);
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);
        weixin.setTextColor(Color.parseColor("#FFE1FF"));

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    private void changeTextColor(int position) {
        if (position == 0) {
            weixin.setTextColor(Color.parseColor("#FFE1FF"));
            tongxunlu.setTextColor(Color.parseColor("#000000"));
            faxian.setTextColor(Color.parseColor("#000000"));
            me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            weixin.setTextColor(Color.parseColor("#000000"));
            tongxunlu.setTextColor(Color.parseColor("#FFE1FF"));
            faxian.setTextColor(Color.parseColor("#000000"));
            me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 2) {
            weixin.setTextColor(Color.parseColor("#000000"));
            tongxunlu.setTextColor(Color.parseColor("#000000"));
            faxian.setTextColor(Color.parseColor("#FFE1FF"));
            me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 3) {
            weixin.setTextColor(Color.parseColor("#000000"));
            tongxunlu.setTextColor(Color.parseColor("#000000"));
            faxian.setTextColor(Color.parseColor("#000000"));
            me.setTextColor(Color.parseColor("#FFE1FF"));
        }
    }

    private void initViews() {
        title = findViewById(R.id.title);
        weixin = findViewById(R.id.weixin);
        tongxunlu = findViewById(R.id.tongxunlu);
        faxian = findViewById(R.id.faxian);
        me = findViewById(R.id.me);

        weixin.setOnClickListener(this);
        tongxunlu.setOnClickListener(this);
        faxian.setOnClickListener(this);
        me.setOnClickListener(this);

        vp = findViewById(R.id.mainVP);
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();
        FourFragment fourFragment = new FourFragment();

        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(fourFragment);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weixin:
                vp.setCurrentItem(0, true);
                break;
            case R.id.tongxunlu:
                vp.setCurrentItem(1, true);
                break;
            case R.id.faxian:
                vp.setCurrentItem(2, true);
                break;
            case R.id.me:
                vp.setCurrentItem(3, true);
                break;
        }
    }


    private static class FragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}