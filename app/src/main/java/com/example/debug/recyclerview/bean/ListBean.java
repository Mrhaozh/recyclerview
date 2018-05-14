package com.example.debug.recyclerview.bean;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.example.debug.recyclerview.MainActivity;
import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.utils.CenterAlignImageSpan;

import java.util.ArrayList;
import java.util.List;

public class ListBean {
    public ListBean() {
    }
    private List<String> likelist=new ArrayList<String>();
    private List<String> urlList = new ArrayList<>();
    private StringBuilder sb =new StringBuilder();
    private String nickName;
    private List<Comment> CommentList =new ArrayList<Comment>();
    private boolean likeState;
    public List<Comment> getCommentList(){
        return CommentList;
    }
    public void setCommentList(List<Comment> CommentList){
        this.CommentList=CommentList;
    }
   /* public void setList(List<String> urlList) {
        this.urlList = urlList;
    }*/
    public List<String> getList() {
        return urlList;
    }
    public StringBuilder setsb(){
       return sb;
    }
    public void setsbstr(String str){
        this.sb=sb.append(str);
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
    public boolean getlikeState(){
        return likeState;
    }
    public void setlikeState(boolean likeState){
        this.likeState=likeState;
    }
    public String getnickName() {
        return nickName;
    }
    public List<String> getlikelist(){
        return likelist;
    }
}
