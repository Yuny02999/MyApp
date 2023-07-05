package com.example.helloworld.TaoBao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.helloworld.R;
import com.example.helloworld.TaoBao.Seek_status_bar.Seek_Message;
import com.example.helloworld.TaoBao.Seek_status_bar.Seek_My;
import com.example.helloworld.TaoBao.Seek_status_bar.Seek_ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class TaoBao_MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView SignIn,VIP_Picture,seek_scan,seek_img2,flyCat,hot,
            flyCat_,farm,flyCat_supermarket,top_up,fresh,Gold,
            auction,classification;
    TextView signIn_Text,recommended,VIP_Text,subscription,Homepage,message,shoppingCart,my;
    Button find;
    EditText seek_edt1;
    ViewPager viewPager1,viewPager2;
    RatingBar ratingBar;

    private MyPagerAdapter mAdapter;
    private ViewPager MyRecommended;

    MyAdapter mMyAdapter;



    public static class News{
        public String title;
        public String content;

    }


    List<News> mNewsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        initViews();

        for(int i = 0;i < 10; i++){
            News news = new News();
            news.title = "标题"+i ;
            news.content = "内容"+i;
            mNewsList.add(news);
        }

        LayoutInflater inflater = LayoutInflater.from(TaoBao_MainActivity.this);
        View layout = inflater.inflate(R.layout.fragment__recommend,null);
        RecyclerView Rec1 = layout.findViewById(R.id.Rec1);


//        RecyclerView Rec1 = findViewById(R.id.Rec1);
        mMyAdapter = new MyAdapter();
        Rec1.setAdapter(mMyAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(TaoBao_MainActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        Rec1.setLayoutManager(layoutManager);

        Rec1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });


        MyRecommended.setOffscreenPageLimit(2);     //屏幕页数限制
        MyRecommended.setAdapter(mAdapter);
        MyRecommended.setCurrentItem(0);        //当前页
        recommended.setTextColor(Color.parseColor("#FFE1FF"));
        recommended.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);     //添加文字下划线


        Homepage.setTextColor(Color.parseColor("#FFE1FF"));
        Homepage.setTextSize(20);
        MyRecommended.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTextStyle(position);
            }


            @Override
            public void onPageScrollStateChanged(int arg1) {

            }
        });


        ArrayList<View> aList = new ArrayList<View>();
        LayoutInflater li1 = getLayoutInflater();       //LayoutInflater用来找布局并且实例化
        aList.add(li1.inflate(R.layout.fragment__recommend,null,false));
        aList.add(li1.inflate(R.layout.fragment_subscription,null,false));
        mAdapter = new MyPagerAdapter(aList);
        MyRecommended.setAdapter(mAdapter);

    }



    private void changeTextStyle(int position) {
        if (position == 0){
            recommended.setTextColor(Color.parseColor("#FFE1FF"));
            recommended.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            subscription.setTextColor(Color.parseColor("#ffffff"));
            subscription.getPaint().setFlags(0);
        }else if (position == 1){
            recommended.setTextColor(Color.parseColor("#ffffff"));
            recommended.getPaint().setFlags(0);
            subscription.setTextColor(Color.parseColor("#ffe1ff"));
            subscription.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
    }


    private void initViews() {
        Homepage = findViewById(R.id.Homepage);
        message = findViewById(R.id.message);
        shoppingCart = findViewById(R.id.shoppingCart);
        my = findViewById(R.id.my);
        SignIn = findViewById(R.id.SignIn);
        VIP_Picture = findViewById(R.id.VIP_Picture);
        seek_scan = findViewById(R.id.seek_scan);
        seek_img2 = findViewById(R.id.seek_img2);
        flyCat = findViewById(R.id.flyCat);
        hot = findViewById(R.id.hot);
        farm = findViewById(R.id.farm);
        flyCat_supermarket = findViewById(R.id.flyCat_supermarket);
        top_up = findViewById(R.id.top_up);
        flyCat_ =  findViewById(R.id.flyCat_);
        fresh = findViewById(R.id.fresh);
        Gold = findViewById(R.id.Gold);
        auction = findViewById(R.id.auction);
        classification = findViewById(R.id.classification);
        signIn_Text = findViewById(R.id.signIn_Text);
        VIP_Text = findViewById(R.id.VIP_Text);
        recommended = findViewById(R.id.recommended);
        find = findViewById(R.id.find);
        MyRecommended = findViewById(R.id.MyRecommended);
        subscription = findViewById(R.id.subscription);
        seek_edt1 = findViewById(R.id.seek_edt1);
        viewPager2 = findViewById(R.id.viewpager2);
        ratingBar = findViewById(R.id.ratingBar);


        Homepage.setOnClickListener(this);
        message.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);
        my.setOnClickListener(this);
        subscription.setOnClickListener(this);
        recommended.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subscription:
                MyRecommended.setCurrentItem(1,true);
                break;

            case R.id.recommended:
                MyRecommended.setCurrentItem(0,true);
                break;

            case R.id.Homepage:
                Homepage.setTextColor(Color.parseColor("#FFE1FF"));
                Homepage.setTextSize(20);
                message.setTextColor(Color.parseColor("#ffffff"));
                message.setTextSize(15);
                shoppingCart.setTextColor(Color.parseColor("#ffffff"));
                shoppingCart.setTextSize(15);
                my.setTextColor(Color.parseColor("#ffffff"));
                my.setTextSize(15);
                break;
            case R.id.message:
                Homepage.setTextColor(Color.parseColor("#ffffff"));
                Homepage.setTextSize(15);
                message.setTextColor(Color.parseColor("#FFE1FF"));
                message.setTextSize(20);
                shoppingCart.setTextColor(Color.parseColor("#ffffff"));
                shoppingCart.setTextSize(15);
                my.setTextColor(Color.parseColor("#ffffff"));
                my.setTextSize(15);
                Intent intent = new Intent(this, Seek_Message.class);
                startActivity(intent);
                break;
            case R.id.shoppingCart:
                Homepage.setTextColor(Color.parseColor("#ffffff"));
                Homepage.setTextSize(15);
                message.setTextColor(Color.parseColor("#ffffff"));
                message.setTextSize(15);
                shoppingCart.setTextColor(Color.parseColor("#FFE1FF"));
                shoppingCart.setTextSize(20);
                my.setTextColor(Color.parseColor("#ffffff"));
                my.setTextSize(15);
                Intent intent1 = new Intent(this, Seek_ShoppingCart.class);
                startActivity(intent1);
                break;
            case R.id.my:
                Homepage.setTextColor(Color.parseColor("#ffffff"));
                Homepage.setTextSize(15);
                message.setTextColor(Color.parseColor("#ffffff"));
                message.setTextSize(15);
                shoppingCart.setTextColor(Color.parseColor("#ffffff"));
                shoppingCart.setTextSize(15);
                my.setTextColor(Color.parseColor("#FFE1FF"));
                my.setTextSize(20);
                Intent intent2 = new Intent(this, Seek_My.class);
                startActivity(intent2);
                break;
        }
    }

    public static class MyPagerAdapter extends PagerAdapter{
        private ArrayList<View> viewLists;
        public MyPagerAdapter(){
        }

        public MyPagerAdapter(ArrayList<View> viewLists){
            super();
            this.viewLists = viewLists;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position){
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
    }



    class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {
        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(TaoBao_MainActivity.this,R.layout.item_list,null);
            return new MyViewHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHoder holder, final int position) {
            News news = mNewsList.get(position);
            holder.mTitle.setText(news.title);
            holder.mTitleContent.setText(news.content);

        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    static class MyViewHoder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mTitleContent;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.textV1);
            mTitleContent = itemView.findViewById(R.id.textV2);
        }
    }
}