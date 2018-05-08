package com.example.debug.recyclerview.bean;

import android.text.LoginFilter;

public class Comment {
    private String UserName;
    private String receiver;
    private int Uid;
    private String Content;
    public Comment(String UserName,int Uid,String Content,String receiver){
        this.UserName=UserName;
        this.Uid=Uid;
        this.Content=Content;
        this.receiver=receiver;
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
    public String getReceiver(){
        return receiver;
    }
}
