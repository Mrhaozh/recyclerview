package com.example.debug.recyclerview.bean;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MessageEvent {
        public String msg;
        public ListView listView;
        public View v;
        public int position;
        public MessageEvent(ListView listView,View v,int position){
            this.listView=listView;
            this.v=v;
            this.position=position;
        }
        public MessageEvent(String msg,ListView listView,View v,int position){
            this.listView=listView;
            this.v=v;
            this.position=position;
            this.msg=msg;
        }
}
