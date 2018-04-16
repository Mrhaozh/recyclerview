package com.example.debug.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class ListBean {
    public ListBean(){}
    private List<String> urlList =new ArrayList<>();

    private  String nickName;
    public void setList(List<String> urlList){
        this.urlList=urlList;
    }
    public List<String> getList(){
        return urlList;
    }
    public void setnickName(String nickName){
        this.nickName=nickName;
    }
    public String getnickName(){
        return nickName;
    }
}
