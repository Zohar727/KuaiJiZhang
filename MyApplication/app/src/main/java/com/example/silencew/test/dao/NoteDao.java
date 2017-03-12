package com.example.silencew.test.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silencew.test.bean.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silence.w on 2016-12-15.
 */

public class NoteDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public NoteDao(Context context){
        helper = new DBOpenHelper(context);
    }

    //添加便签信息
    public void add(Note note){
        db =helper.getWritableDatabase();
        db.execSQL("insert into note(id,time,vNote) values (?,?,?)",
                new Object[]{
                        note.getId(),note.getTime(),note.getvNote()
                });
        db.close();
    }
    //查找便签信息
    public List<Note> queryAll(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select id,time,vNote from note",null);
        List<Note> list = new ArrayList<Note>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String vNote = cursor.getString(cursor.getColumnIndex("vNote"));
            list.add(new Note(id,time,vNote));
        }
        cursor.close();
        db.close();
        return list;
    }
    //删除便签信息
    public int delete(int id){
        db = helper.getWritableDatabase();
        int count = db.delete("note","id=?",new String[]{String.valueOf(id)});
        db.close();
        return count;
    }
    //更新便签信息
    public void update(Note note){
        db = helper.getWritableDatabase();
        db.execSQL(
                "update note set vNote = ? where id = ?",
                new Object[]{note.getvNote(),note.getId()});

    }
    //获得最大编号
    public int getMaxId(){
        db =helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from note",
                null);//获取列表中最大编号
        while (cursor.moveToNext()){//访问cursor中最后一条数据
            return cursor.getInt(0);
        }
        db.close();
        return 0;
    }
}
