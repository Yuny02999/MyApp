package com.example.helloworld.AllService;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.helloworld.SQLite_Activity;

import java.util.ArrayList;

public class MyHelper extends SQLiteOpenHelper {
    //    private SQLiteDatabase db;
    public MyHelper(Context context) {
        super(context, "sqlite.db", null, 1);
//        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = ("Create table table1(id Integer primary key autoincrement, user varchar(20), pwd varchar(20))");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


//    public Boolean add(String name, String pwd){
//        ContentValues cv = new ContentValues();
//        cv.put("name",name);
//        cv.put("pwd",pwd);
//        long i = db.insert("table1",null,cv);
//        if (i > 0){
//            Log.i("==========","插入成功");
//            return true;
//        }
//        return false;
//    }
//
//    public Boolean del(String name){
//        long i = db.delete("table1","name=?",new String[]{name});
//        if (i > 0){
//            Log.i("==========","删除成功");
//            return true;
//        }
//        return false;
//    }
//
//    public boolean change(String name, String NewPwd){
//        ContentValues cv = new ContentValues();
//        cv.put("pwd",NewPwd);
//        long i = db.update("table1",cv,"name=?",new String[]{name});
//        if (i > 0){
//            Log.i("==========","更新成功");
//            return true;
//        }
//        return false;
//    }
//
//    public ArrayList getAll(){
//        ArrayList arrayList = new ArrayList();
//        Cursor cursor = db.query("table1",null,
//                null,null,null,null,null,null);
//        while (cursor.moveToNext()){
//            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("user"));
//            @SuppressLint("Range") String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
//            AllUser u = new AllUser(name,pwd);
//            arrayList.add(u);
//        }
//        return arrayList;
//    }
}