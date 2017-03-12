package com.example.silencew.test.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silencew.test.bean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silence.w on 2016-12-14.
 */

public class AllBillDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    private int id;
    public AllBillDao(Context context){
        helper = new DBOpenHelper(context);
    }

    //查询收入和支出的全部账单
    public List<Spend> queryAll(){
        db = helper.getReadableDatabase();
        String oType = "spend";
        Cursor cursor = db.rawQuery(
                "select id,money,time,type,mark,mType from spend where mType =?",
                new String[]{String.valueOf(oType)});
        List<Spend> list = new ArrayList<Spend>();
        while (cursor.moveToNext()){
            int mid = cursor.getInt(cursor.getColumnIndex("id"));
            double money =cursor.getDouble(cursor.getColumnIndex("money"));
            String time =cursor.getString(cursor.getColumnIndex("time"));
            int type =cursor.getInt(cursor.getColumnIndex("type"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String mType = cursor.getString(cursor.getColumnIndex("mType"));
            list.add(new Spend(mid,money,time,type,mark,mType));
        }
        cursor.close();
        oType = "income";
        Cursor cursor1 =db.rawQuery(
                "select id,money,time,type,mark,mType from income where mType =?",
                new String[]{String.valueOf(oType)});
        while (cursor1.moveToNext()){
            int mid = cursor1.getInt(cursor1.getColumnIndex("id"));
            double money =cursor1.getDouble(cursor1.getColumnIndex("money"));
            String time =cursor1.getString(cursor1.getColumnIndex("time"));
            int type =cursor1.getInt(cursor1.getColumnIndex("type"));
            String mark = cursor1.getString(cursor1.getColumnIndex("mark"));
            String mType = cursor1.getString(cursor1.getColumnIndex("mType"));
            list.add(new Spend(mid,money,time,type,mark,mType));
        }
        cursor1.close();
        db.close();
        return list;
    }

    //删除收入数据
    public int delete(int id, String mType){
        db = helper.getWritableDatabase();
        int count;
        if(mType.equals("spend")){
            count = db.delete("spend","id=?",new String[]{String.valueOf(id)});
        }else{
            count = db.delete("income","id=?",new String[]{String.valueOf(id)});
        }
        db.close();
        return count;
    }
}
