package com.example.debug.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.bean.Comment;

import java.util.List;

public class CommentListAdapter extends BaseAdapter{
    private Context context;
    private List<Comment> CommentList;
    public CommentListAdapter(Context context,List<Comment> CommentList){
        this.context=context;
        this.CommentList=CommentList;
    }
    @Override
    public int getCount() {
        return CommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.uname=convertView.findViewById(R.id.uname);
            viewHolder.content=convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
            viewHolder.uname.setText(CommentList.get(position).getUserName());
            viewHolder.content.setText(CommentList.get(position).getContent());
        return convertView;
    }
    private class ViewHolder {
        TextView uname;
        TextView content;
    }
}
