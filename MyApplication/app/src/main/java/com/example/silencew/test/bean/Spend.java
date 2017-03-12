package com.example.silencew.test.bean;

/**
 * Created by silence.w on 2016-12-13.
 */

public class Spend {
    private int id;
    private double money;
    private String time;
    private int type;
    private String mark;
    private String mType;

    public Spend(int id,double m,String time,int type,String mark,String mType){
        super();
        this.id=id;
        this.money=m;
        this.time=time;
        this.type=type;
        this.mark=mark;
        this.mType=mType;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }
    public double getMoney(){
        return  money;
    }
    public void setMoeney(double money){
        this.money=money;
    }
    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type=type;
    }
    public String getMark(){
        return mark;
    }
    public void setMark(String mark){
        this.mark=mark;
    }
    public String getmType(){
        return mType;
    }
    public void setmType(String mType){
        this.mType=mType;
    }
}
