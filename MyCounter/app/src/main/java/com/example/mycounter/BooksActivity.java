package com.example.mycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    ImageView addView;
    List<Map<String, Object>> List = new ArrayList<>();
    Dialog dialog;
    Dialog dialog_first;
    SimpleAdapter sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        initView();
        sp = new SimpleAdapter(this, getData(),
                R.layout.list_layout,
                new String[]{"title", "sum", "time"},
                new int[]{R.id.title, R.id.sum, R.id.time_text});
        listView.setAdapter(sp);

        listView.setOnItemClickListener((adapterView, view, i, l) -> promptShow(i));
    }

    private void promptShow(int i) {
        dialog_first = new AlertDialog.Builder(this).create();
        dialog_first.show();
        dialog_first.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog_first.getWindow();
        WindowManager.LayoutParams lp = dialog_first.getWindow().getAttributes();
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        lp.width = 500;
        window.setAttributes(lp);
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this)
                .inflate(R.layout.longcheck_layout, null);
        window.setContentView(view);
        final TextView change = view.findViewById(R.id.change);
        final TextView delete = view.findViewById(R.id.delete);

        change.setOnClickListener(view1 -> {
            upData(i);
        });

        delete.setOnClickListener(view1 -> {
            AlertDialog dialog1;
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.count_icon)
                    .setTitle("提示")
                    .setMessage("是否要删除该用例")
                    .setPositiveButton("确定", (dialog2, a) -> {
                        deleteData(i);
                        dialog_first.dismiss();
                    })
                    .setNegativeButton("取消", (dialog2, a) -> dialog2.dismiss());
            dialog1 = builder.create();
            dialog1.show();
        });

    }

    private void upData(int i) {
        dialog_first.dismiss();
        dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this)
                .inflate(R.layout.list_layout3, null);
        window.setContentView(view);

        final LinearLayout layout = view.findViewById(R.id.title_linear);
        layout.setBackgroundResource(android.R.color.transparent);
        final String title = (String) List.get(i).get("title");
        final String sum = (String) List.get(i).get("sum");
        final String time = (String) List.get(i).get("time");

        final TextView title_edit = view.findViewById(R.id.title_edit);
        title_edit.setText(title);
        final EditText sum_edit = view.findViewById(R.id.sum_edit);
        final EditText time_edit = view.findViewById(R.id.time_edit);

        final Button time_btn = view.findViewById(R.id.time_btn);
        final Button success_btn = view.findViewById(R.id.success_btn);

        //获取当前时间
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String nowTime = formatter.format(date);
        time_btn.setOnClickListener(view1 -> {
            time_edit.setText("");
            time_edit.setText(nowTime);
        });
        success_btn.setOnClickListener(v -> {
            MySQLHelper MySQLHelper = new MySQLHelper(getApplicationContext());
            SQLiteDatabase db = MySQLHelper.getWritableDatabase();
            String sum_add = sum_edit.getText().toString();
            String time_add = time_edit.getText().toString();
            if ( sum_add.isEmpty() | time_add.isEmpty()) {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("sum", sum_add);
            cv.put("time", time_add);
            long a = db.update("books", cv, "title=?", new String[]{title});
            if (a > 0){
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                List.clear();
                sp = new SimpleAdapter(this, getData(),
                        R.layout.list_layout,
                        new String[]{"title", "sum", "time"},
                        new int[]{R.id.title, R.id.sum, R.id.time_text});
                listView.setAdapter(sp);
                sp.notifyDataSetChanged();
            }else {
                Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        });
        title_edit.setHint(title);
        sum_edit.setHint(sum);
        time_edit.setHint(time);

    }

    private void deleteData(int i) {
        String title = String.valueOf(List.get(i).get("title"));
        String sum = String.valueOf(List.get(i).get("sum"));
        String time = String.valueOf(List.get(i).get("time"));
        MySQLHelper mySQLHelper = new MySQLHelper(getApplicationContext());
        SQLiteDatabase db = mySQLHelper.getWritableDatabase();
        long dl_title = db.delete("books", "title=?", new String[]{title});
        if (dl_title > 0 ) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        db.close();
        List.clear();
        sp = new SimpleAdapter(this, getData(),
                R.layout.list_layout,
                new String[]{"title", "sum", "time"},
                new int[]{R.id.title, R.id.sum, R.id.time_text});
        listView.setAdapter(sp);
        sp.notifyDataSetChanged();
    }

    private List<Map<String, Object>> getData() {
        MySQLHelper MySQLHelper = new MySQLHelper(getApplicationContext());
        SQLiteDatabase db = MySQLHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query("books", new String[]{"title", "sum", "time"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String sum = cursor.getString(cursor.getColumnIndex("sum"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            Map<String, Object> map = new HashMap<>();
            map.put("title", title);
            map.put("sum", sum);
            map.put("time", time);
            List.add(map);
        }
        return List;
    }

    private void initView() {
        addView = findViewById(R.id.addView);
        listView = findViewById(R.id.listView);

        addView.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addView) {
            makeDialog();
        }
    }

    private void makeDialog() {
        dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //取消掉四个角瑕疵问题
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this)
                .inflate(R.layout.list_layout2, null);
        //自适应弹窗大小
        lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setContentView(view);
        final LinearLayout title_linear = view.findViewById(R.id.title_linear);
        title_linear.setBackgroundResource(android.R.color.transparent);
        final Button success_btn = view.findViewById(R.id.success_btn);
        final EditText title_edit = view.findViewById(R.id.title_edit);
        final EditText sum_edit = view.findViewById(R.id.sum_edit);
        final EditText time_edit = view.findViewById(R.id.time_edit);
        final Button time_btn = view.findViewById(R.id.time_btn);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String nowTime = formatter.format(date);

        time_btn.setOnClickListener(view1 -> time_edit.setText(nowTime));
        success_btn.setOnClickListener(v -> {
            MySQLHelper MySQLHelper = new MySQLHelper(getApplicationContext());
            String title = title_edit.getText().toString();
            String sum = sum_edit.getText().toString();
            String time = time_edit.getText().toString();
            if (title.isEmpty() | sum.isEmpty() | time.isEmpty()) {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            SQLiteDatabase db = MySQLHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            String sql = "select title from books where title=?";
            Cursor cr = db.rawQuery(sql, new String[]{title}, null);
            while (cr.moveToNext()) {
                @SuppressLint("Range") String content = cr.getString(cr.getColumnIndex("title"));
                if (content.length() > 0) {
                    Toast.makeText(this, "用例名重复!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            cv.put("title", title);
            cv.put("sum", sum);
            cv.put("time", time);
            long i = db.insert("books", null, cv);
            if (i > 0) {
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
            }


            db.close();
            dialog.dismiss();
            List.clear();
            sp = new SimpleAdapter(this, getData(),
                    R.layout.list_layout,
                    new String[]{"title", "sum", "time"},
                    new int[]{R.id.title, R.id.sum, R.id.time_text});
            listView.setAdapter(sp);
            sp.notifyDataSetChanged();
        });
    }
}