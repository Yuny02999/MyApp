package com.example.mycounter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;

public class SwipeLayoutAdapter extends BaseSwipeAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Toast toast;

    public SwipeLayoutAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private void mToast(String msg) {
        if (toast!= null){
            toast.cancel();
            toast = null;
        }else {
            toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0 ,0);
            toast.setText(msg);
            toast.show();
        }
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.layout.activity_books;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = inflater.inflate(R.layout.list_layout,null);
        SwipeLayout swipeLayout = v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                mToast("swipeText");
            }
        });

        v.findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mToast("changer");
            }
        });

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }


        });
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {

    }

    @Override
    public int getCount() {
        return 99;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
