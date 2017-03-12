package com.example.silencew.test.bean;

/**
 * Created by silence.w on 2016-12-14.
 */

public class Account {
    private String user;
    private String password;

    public Account (String user, String password){
        super();
        this.user=user;
        this.password= password;
    }

    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }
}

