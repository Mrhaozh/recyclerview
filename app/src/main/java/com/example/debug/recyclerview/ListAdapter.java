package com.example.debug.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends BaseAdapter{
    private Context context;
    private List<ListBean> listData;
    public ListAdapter(Context context, List<ListBean> listData){
        this.context=context;
        this.listData=listData;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.txv=convertView.findViewById(R.id.nickName);
            viewHolder.likeTxv=convertView.findViewById(R.id.likeTxv);
            viewHolder.triicon=convertView.findViewById(R.id.triicon);
            viewHolder.likeLayout=convertView.findViewById(R.id.likeLayout);
            viewHolder.recyclerView=convertView.findViewById(R.id.recycler_view);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.txv.setText(listData.get(position).getnickName());
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(context,listData.get(position).getList());
        viewHolder.recyclerView.setAdapter(recyclerAdapter);
        if(listData.get(position).getList().size()==4){
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context,2, OrientationHelper.VERTICAL,false));
        }else if(listData.get(position).getList().size()==1){
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 1, OrientationHelper.VERTICAL, false));
        }else{
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context,3,OrientationHelper.VERTICAL,false));
        }
        if(listData.get(position).getsb()!=null){
            viewHolder.triicon.setVisibility(View.VISIBLE);
            viewHolder.likeLayout.setVisibility(View.VISIBLE);
            viewHolder.likeTxv.setMovementMethod(LinkMovementMethod.getInstance());
            viewHolder.likeTxv.setText(addClickPart(listData.get(position).getsb()),TextView.BufferType.SPANNABLE);
        }
        return convertView;
    }
    private SpannableStringBuilder addClickPart(String str){
      //  ImageSpan imgspan =new ImageSpan(context,R.drawable.heart);
      //  SpannableString spanStr=new SpannableString("p.");
      //  spanStr.setSpan(imgspan,0,1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        SpannableStringBuilder ssb =new SpannableStringBuilder();
        ssb.append(str);
        String[] likeUsers =str.split(",");
        if(likeUsers.length>0){
            for(int i=0;i<likeUsers.length;i++){
                final String name=likeUsers[i];
                final int start =str.indexOf(name);
                ssb.setSpan(new ClickableSpan(){
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context,name,Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                },start,start+name.length(),0);
            }
        }
        return ssb.append("等" +likeUsers.length+"个人觉得很赞");
    }
    private class ViewHolder{
        TextView txv;
        TextView likeTxv;
        ImageView triicon;
        LinearLayout likeLayout;
        RecyclerView recyclerView;
    }
}
