package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static Toast toast;
    Button open, change;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private static void showToast(Context context, String msg){
        if (toast != null){
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(msg);
        toast.show();
    }

    private void initView() {
        open = findViewById(R.id.open);
        content = findViewById(R.id.content);
        change = findViewById(R.id.change);

        change.setOnClickListener(this);
        open.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MySQL mySQL = new MySQL(MainActivity.this);
        SQLiteDatabase db = mySQL.getReadableDatabase();
        if (view.getId() == R.id.open) {
            if (db.isOpen()) {
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from books", null);
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String data = cursor.getColumnName(cursor.getColumnIndex("name"));
                }
                showToast(this, "123");
                cursor.close();
                db.close();
            }
        }
        if (view.getId() == R.id.change){
            Intent i = new Intent(this,MainActivity2.class);
            startActivity(i);
            showToast(this,"跳转");
        }
    }
}