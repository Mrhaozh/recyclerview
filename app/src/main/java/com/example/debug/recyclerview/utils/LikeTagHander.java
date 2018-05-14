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

import org.xml.sax.XMLReader;

import java.util.HashMap;
import java.util.zip.Inflater;

public class LikeTagHander implements Html.TagHandler {
    public static String LIKE_CLICKER;
    public static int LIKE_INT =-2018;
    private ClickableSpan likeuser;
    private Context context;
    private HashMap<Integer,Integer> hashmap=new HashMap<Integer, Integer>();
    public LikeTagHander(Context context, final onlikerclicklistener listener){
        this.context=context;
        likeuser=new baseclickablespan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(0xFF436B9C);
                ds.setUnderlineText(false);
            }
            @Override
            public void onClick(View widget) {
                if(listener!=null){
                    listener.onlikerclick(widget);
                }
            }
        };
    }
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if(opening){
            int mStart=output.length();
            hashmap.put(LIKE_INT,mStart);
        }else{
            int mEnd=output.length();
            int mStart=hashmap.get(LIKE_INT);
            output.setSpan(new TextAppearanceSpan(context,R.style.Comment),mStart,mEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    abstract class baseclickablespan extends ClickableSpan{
        public baseclickablespan() {
            super();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
        }

        @Override
        public void onClick(View widget) {

        }
    }
    public interface onlikerclicklistener{
        void onlikerclick(View v);
    }
}
