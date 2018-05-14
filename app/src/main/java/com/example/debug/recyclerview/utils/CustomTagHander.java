package com.example.debug.recyclerview.utils;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;

import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.bean.Comment;

import org.xml.sax.XMLReader;

import java.util.HashMap;

public class CustomTagHander implements Html.TagHandler{
    public static final String TAG_COMMENTATOR="commentator"; //评论者
    public static final String TAG_RECEIVER ="receiver";//评论接收者
    public static final String TAG_CONTENT ="content";//评论内容

    public static final int KEY_COMMENTATOR=-2016;
    public static final int KEY_RECEIVER=-20162;

    public static final int KEY_COMMENTATOR_START=1;
    public static final int KEY_RECEIVER_START=11;
    public static final int KEY_CONTENT_START=21;
    private ClickableSpan mCommentatorSpan,mReceiverSpan,mContentSpan;
    private Context mContext;
    private HashMap<Integer,Integer> mMaps =new HashMap<Integer, Integer>();
    public CustomTagHander(final Context context,final OnCommentClickListener listener){
        mContext=context;
        mCommentatorSpan=new BaseClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(listener!=null){
                    String comment =(String) widget.getTag(KEY_COMMENTATOR);
                    listener.onCommentatorClick(widget,comment);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(0xFF436B9C);
                ds.setUnderlineText(false);
            }
        };
        mReceiverSpan=new BaseClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(listener !=null){
                    String comment=(String) widget.getTag(KEY_RECEIVER);
                    listener.onReceiverClick(widget,comment);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(0xFF436B9C);

            }
        };
        mContentSpan=new BaseClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(listener!=null){
                    String comment =(String)widget.getTag(KEY_COMMENTATOR);
                    String receiver=(String)widget.getTag(KEY_RECEIVER);
                    listener.onContentClick(widget,comment,receiver);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(0xff000000);
            }
        };

    }
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if(!tag.toLowerCase().equals(TAG_COMMENTATOR)&& !tag.toLowerCase().equals(TAG_RECEIVER) && !tag.toLowerCase().equals(TAG_CONTENT)){
            return;
        }
        if(opening){
            int mStart =output.length();
            if(tag.toLowerCase().equals(TAG_COMMENTATOR)){
                mMaps.put(KEY_COMMENTATOR_START,mStart);
            }else if(tag.toLowerCase().equals(TAG_RECEIVER)){
                mMaps.put(KEY_RECEIVER_START,mStart);
            }else if(tag.toLowerCase().equals(TAG_CONTENT)){
                mMaps.put(KEY_CONTENT_START,mStart);
            }
        }else{
            int mEnd =output.length();
            if(tag.toLowerCase().equals(TAG_COMMENTATOR)){
                int mStart =mMaps.get(KEY_COMMENTATOR_START);
                output.setSpan(new TextAppearanceSpan(mContext, R.style.Comment),mStart,mEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                output.setSpan(mCommentatorSpan,mStart,mEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else if(tag.toLowerCase().equals(TAG_RECEIVER)){
                int mStart =mMaps.get(KEY_RECEIVER_START);
                output.setSpan(new TextAppearanceSpan(mContext,R.style.Comment),mStart,mEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                output.setSpan(mReceiverSpan,mStart,mEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else if(tag.toLowerCase().equals(TAG_CONTENT)){
                int mStart =mMaps.get(KEY_CONTENT_START);
                    output.setSpan(new TextAppearanceSpan(mContext,R.style.Comment),mStart,mEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    output.setSpan(mContentSpan,mStart,mEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    public interface OnCommentClickListener{
        void onCommentatorClick(View view,String comment);
        void onReceiverClick(View view,String receiver);
        void onContentClick(View view,String comment,String receiver);
    }
    abstract class BaseClickableSpan extends ClickableSpan{
        public BaseClickableSpan(){

        }

        @Override
        public void onClick(View widget) {

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
            super.updateDrawState(ds);
        }
    }
}
