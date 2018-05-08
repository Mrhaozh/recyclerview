package com.example.debug.recyclerview.bean;

import java.util.ArrayList;
import java.util.List;

public class ListBean {
    public ListBean() {
    }
    private List<String> urlList = new ArrayList<>();
    private StringBuilder sb =new StringBuilder();
    private String nickName;
    private List<Comment> CommentList =new ArrayList<Comment>();

    public List<Comment> getCommentList(){
        return CommentList;
    }
    public void setCommentList(List<Comment> CommentList){
        this.CommentList=CommentList;
    }
    public void setList(List<String> urlList) {
        this.urlList = urlList;
    }
    public List<String> getList() {
        return urlList;
    }
    public StringBuilder setsb(){
       return sb;
    }
    public String getsb(){
        if(sb.toString().length()>0) {
            return sb.substring(0, sb.lastIndexOf(",")).toString();
        }else{
            return null;
        }
        }
    public void setnickName(String nickName) {
        this.nickName = nickName;
    }

    public String getnickName() {
        return nickName;
    }
}
