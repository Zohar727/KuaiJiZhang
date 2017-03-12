package com.example.silencew.test.dao;

/**
 * Created by silence.w on 2016-12-13.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silencew.test.bean.*;

import java.util.ArrayList;
import java.util.List;

public class SpendDao {
    private DBOpenHelper helper;//创建DBO对象
    private SQLiteDatabase db;//创建数据库对象
    private int id;
    public SpendDao(Context context){
        helper = new DBOpenHelper(context);
    }

    //添加支出信息
    public void add(Spend spend){
        db = helper.getWritableDatabase();
        db.execSQL("insert into spend(id,money,time,type,mark,mType) values(?,?,?,?,?,?)",
                new Object[]{
                        spend.getId(),spend.getMoney(),spend.getTime(),
                        spend.getType(),spend.getMark(),spend.getmType()
                });
    }
    //查询所有支出数据
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
        db.close();
        return list;
    }
    //删除支出数据
    public int delete(int id){
        db=helper.getWritableDatabase();
        int count = db.delete("spend","id=?",new String[]{String.valueOf(id)});
        db.close();
        return count;
    }
    //更新输入数据
    public void update(Spend spend){
        db = helper.getWritableDatabase();
        db.execSQL(
                "update spend set money =?,time = ?,type = ?,mark =? where id =?",
                new Object[]{
                        spend.getMoney(),spend.getTime(),spend.getType(),
                        spend.getMark(),spend.getId()});
    }
    //获取当前支出数据最大编号
    public int getMaxId(String mType){
        db =helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from spend where mType=?",
                new String[]{String.valueOf(mType)});//获取列表中最大编号
        while (cursor.moveToNext()){//访问cursor中最后一条数据
            return cursor.getInt(0);
        }
        db.close();
        return 0;
    }
}
