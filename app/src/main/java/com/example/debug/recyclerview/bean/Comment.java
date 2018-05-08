package com.example.debug.recyclerview.bean;

import android.text.LoginFilter;

public class Comment {
    private String UserName;
    private int Uid;
    private String Content;
    public Comment(String UserName,int Uid,String Content){
        this.UserName=UserName;
        this.Uid=Uid;
        this.Content=Content;
    }
    public String getUserName(){
        return UserName;
    }
    public int getUid(){
        return Uid;
    }
    public String getContent(){
        return Content;
    }
}
