package com.example.silencew.test.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by silence.w on 2016-12-12.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    private static final String DBNAME = "money.db";

    public DBOpenHelper(Context context){
        super(context,"money.db",null,2);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table income(id INTEGER   NOT NULL,money decimal,time varchar(10),type integer ,mark varchar(200) ,mType varchar(20))");
        db.execSQL("create table spend(id INTEGER   NOT NULL,money decimal,time varchar(10),type integer ,mark varchar(200) ,mType varchar(20))");
        db.execSQL("create table account(user varchar(20) NOT NULL ,password varchar(20))");
        db.execSQL("create table note(id INTEGER   NOT NULL,time varchar(20),vNote varchar(1000))");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("");
    }
}
