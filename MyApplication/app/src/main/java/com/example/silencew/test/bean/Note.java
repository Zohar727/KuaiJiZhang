package com.example.silencew.test.bean;

import java.util.Set;

/**
 * Created by silence.w on 2016-12-15.
 */

public class Note {
    private int id;
    private String time;
    private String vNote;

    public Note(int id,String time,String vNote){
        super();
        this.id=id;
        this.time=time;
        this.vNote=vNote;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTime(){
        return time;
    }
    public String getvNote(){
        return vNote;
    }
}
