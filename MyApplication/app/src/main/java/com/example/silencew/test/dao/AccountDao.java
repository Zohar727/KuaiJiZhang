package com.example.silencew.test.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.silencew.test.bean.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silence.w on 2016-12-14.
 */

public class AccountDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public AccountDao(Context context){
        helper = new DBOpenHelper(context);
    }
    public void add(Account account){
        db = helper.getWritableDatabase();
        db.execSQL("insert into account(user,password) values(?,?)",
                new Object[]{
                        account.getUser(),account.getPassword()
                });

    }

    //查询密码
//    public List<Account> query(String user){
//        db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(
//                "select user,password from account where user = ?",
//                new String[]{String.valueOf(user)});
//        List<Account> list = new ArrayList<Account>();
//        while (cursor.moveToNext()){
//            String ouser = cursor.getString(cursor.getColumnIndex("user"));
//            String opassword = cursor.getString(cursor.getColumnIndex("password"));
//            list.add(new Account(ouser,opassword));
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
     public Account query(String user){
         db = helper.getReadableDatabase();
         Cursor cursor = db.rawQuery(
                 "select user,password from account where user =?",
                 new String[]{String.valueOf(user)});
         if(cursor.moveToNext()){
             return new Account(cursor.getString(cursor.getColumnIndex("user")),
                     cursor.getString(cursor.getColumnIndex("password")));
         }
         return null;
     }

    //更新密码
    public void update(Account account){
        db = helper.getWritableDatabase();
        db.execSQL(
                "update account set password = ? where user = ? ",
                new Object[]{
                        account.getPassword(),account.getUser()
                });
    }


}
