package com.example.silencew.test.dao;

/**
 * Created by silence.w on 2016-12-12.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silencew.test.bean.*;

import java.util.ArrayList;
import java.util.List;

public class IncomeDao {
    private DBOpenHelper helper;//创建DBO对象
    private SQLiteDatabase db;//创建数据库对象
    private int id;
    public IncomeDao(Context context){
        helper=new DBOpenHelper(context);
    }

    //添加收入信息
    public void add(Income income){
        db = helper.getWritableDatabase();//初始化数据库对象
        db.execSQL("insert into income(id,money,time,type,mark,mType) values(?,?,?,?,?,?)",
                new Object[]{
                        income.getId(),income.getMoney(),
                        income.getTime(),income.getType(),
                        income.getMark(),income.getmType()
                });
    }

    //查询收入信息
    public Income find(String mType) {
        db = helper.getWritableDatabase();//初始化数据库对象
        Cursor cursor = db.rawQuery(
                "select id,money,time,type,mark from income where mType = ?",
                new String[]{String.valueOf(mType)});
        if (cursor.moveToFirst()) {
            //将遍历的收入信息存储搭配income类中
            return new Income(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getInt(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("mark")),
                    cursor.getString(cursor.getColumnIndex("mType")));
        }
        return null;
    }
    //查询所有收入数据
    public List<Income> queryAll(){
        db = helper.getReadableDatabase();
        String oType="income";
        Cursor cursor = db.rawQuery(
                "select id,money,time,type,mark,mType from income where mType = ?",
                new String[]{String.valueOf(oType)});
        List<Income> list = new ArrayList<Income>();
        while (cursor.moveToNext()){
                    int mid = cursor.getInt(cursor.getColumnIndex("id"));
                    double money =cursor.getDouble(cursor.getColumnIndex("money"));
                    String time =cursor.getString(cursor.getColumnIndex("time"));
                    int type =cursor.getInt(cursor.getColumnIndex("type"));
                    String mark = cursor.getString(cursor.getColumnIndex("mark"));
                    String mType = cursor.getString(cursor.getColumnIndex("mType"));
                    list.add(new Income(mid,money,time,type,mark,mType));
        }
        cursor.close();
        db.close();
        return list;
    }
    //删除收入数据
    public int delete(int id){
        db = helper.getWritableDatabase();
        int count = db.delete("income","id=?",new String[]{String.valueOf(id)});
        db.close();
        return count;

    }
    //更新收入数据
    public void update(Income income){
        db = helper.getWritableDatabase();
        db.execSQL(
                "update income set money =?,time = ?,type = ?,mark =? where id =?",
                new Object[]{
                        income.getMoney(),income.getTime(),income.getType(),
                        income.getMark(),income.getId()});
    }
    //获取当前收入数据最大编号
    public int getMaxId(String mType){
        db =helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from income where mType=?",
                new String[]{String.valueOf(mType)});//获取列表中最大编号
        while (cursor.moveToNext()){//访问cursor中最后一条数据
            return cursor.getInt(0);
        }
        db.close();
        return 0;
    }

}
