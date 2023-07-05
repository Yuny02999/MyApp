package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.AllService.MyHelper;

public class SQLite_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt1, edt2;
    private Button add_data, up_data, del, findAll;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();

        add_data.setOnClickListener(this);
        up_data.setOnClickListener(this);
        del.setOnClickListener(this);
        findAll.setOnClickListener(this);

    }

    private void initView() {
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        add_data = findViewById(R.id.add_data);
        up_data = findViewById(R.id.up_data);
        del = findViewById(R.id.del);
        findAll = findViewById(R.id.findAll);
        tv1 = findViewById(R.id.tv1);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        MyHelper helper = new MyHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Dialog dialog;
        switch (v.getId()) {
            case R.id.add_data:
                add_();
                break;


            case R.id.del:
                @SuppressLint("InflateParams") View view_dialog = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
                dialog = new AlertDialog.Builder(this).create();
                dialog.show();
                //允许获得焦点，弹出输入法
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                //可以被关闭
                dialog.setCancelable(true);
                //获取Window对象
                Window window = dialog.getWindow();
                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                lp.width = 700;
                lp.height = 500;
                window.setAttributes(lp);
                window.setContentView(view_dialog);
                final EditText ed1 = view_dialog.findViewById(R.id.user);
                Button button1 = view_dialog.findViewById(R.id.button1);
                button1.setOnClickListener(v1 -> {
                    String editView_data = ed1.getText().toString();
                    if (editView_data.isEmpty()) {
                        Toast.makeText(SQLite_Activity.this, "user为空", Toast.LENGTH_SHORT).show();
                    } else {
                        db.delete("table1", "user=?", new String[]{editView_data});
                        Toast.makeText(SQLite_Activity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                break;


            case R.id.findAll:
                Cursor cursor = db.query("table1", new String[]{"user", "pwd"},
                        null, null, null, null, null, null);
                String textData = "";
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex("user"));
                    @SuppressLint("Range") String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                    textData = textData + "user: " + user + "\n" + "pwd: " + pwd + "\n" + "------------------------" + "\n";
                }
                tv1.setText(textData);
                cursor.close();
                break;


            case R.id.up_data:
                dialog = new AlertDialog.Builder(this).create();
                dialog.show();
                //弹出输入法
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                //允许取消
                dialog.setCancelable(true);
                Window window2 = dialog.getWindow();
                WindowManager.LayoutParams lp2 = dialog.getWindow().getAttributes();
                lp2.width = 700;
                lp2.height = 500;
                window2.setAttributes(lp2);
                @SuppressLint("InflateParams") View view_dialog2 = LayoutInflater.from(this).inflate(R.layout.change_layout2, null);
                window2.setContentView(view_dialog2);
                final EditText change_edt2 = view_dialog2.findViewById(R.id.change_edt2);
                final EditText change_edt3 = view_dialog2.findViewById(R.id.change_edt3);
                Button del_button2 = view_dialog2.findViewById(R.id.del_button2);
                del_button2.setOnClickListener(view -> {
                    String change_edt2_data = change_edt2.getText().toString();
                    String change_edt3_data = change_edt3.getText().toString();
                    if (change_edt2_data.isEmpty() | change_edt3_data.isEmpty()) {
                        Toast.makeText(SQLite_Activity.this, "内容为空", Toast.LENGTH_SHORT).show();
                    } else {
                        cv.put("user", change_edt2_data);
                        cv.put("pwd", change_edt3_data);
                        db.update("table1", cv, "user=?", new String[]{change_edt2_data});
                        Toast.makeText(SQLite_Activity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }


    private void add_() {
        MyHelper helper = new MyHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String n = edt1.getText().toString();
        String p = edt2.getText().toString();
        if (n.isEmpty() | p.isEmpty()) {
            Toast toast = Toast.makeText(this, "账号或密码为空", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return;
        }
        @SuppressLint("Recycle") Cursor cursor_data = db.query("table1",
                new String[]{"user"},
                "user=?",
                new String[]{n},
                null, null, null);
        while (cursor_data.moveToNext()){
            @SuppressLint("Range") String cursor_ = cursor_data.getString(cursor_data.getColumnIndex("user"));
            if (cursor_.equals(n)){
                Toast.makeText(this, "user已存在", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        cv.put("user", n);
        cv.put("pwd", p);
        long i = db.insert("table1", null, cv);
        if (i > 0) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }
}