package com.example.debug.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
            viewHolder.pl=convertView.findViewById(R.id.pl);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.txv.setText(listData.get(position).getnickName());
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(context,listData.get(position).getList());
        viewHolder.recyclerView.setAdapter(recyclerAdapter);
        if(listData.get(position).getList().size()==4){
            ViewGroup.LayoutParams params=viewHolder.recyclerView.getLayoutParams();
//            params.width=160;
            int getwidth=dp2px(context,180);
            params.width=getwidth;
            viewHolder.recyclerView.setLayoutParams(params);
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context,2, OrientationHelper.VERTICAL,false));
        }else if(listData.get(position).getList().size()==1){
            ViewGroup.LayoutParams params=viewHolder.recyclerView.getLayoutParams();
//            params.width=160;
            int getwidth=dp2px(context,270);
            params.width=getwidth;
            viewHolder.recyclerView.setLayoutParams(params);
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 1, OrientationHelper.VERTICAL, false));
        }else{
            ViewGroup.LayoutParams params=viewHolder.recyclerView.getLayoutParams();
//            params.width=160;
            int getwidth=dp2px(context,270);
            params.width=getwidth;
            viewHolder.recyclerView.setLayoutParams(params);
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context,3,OrientationHelper.VERTICAL,false));
        }
        if(listData.get(position).getsb().length()>1){
            viewHolder.triicon.setVisibility(View.VISIBLE);
            viewHolder.likeLayout.setVisibility(View.VISIBLE);
            viewHolder.likeTxv.setMovementMethod(LinkMovementMethod.getInstance());
            viewHolder.likeTxv.setText(addClickPart(listData.get(position).getsb()),TextView.BufferType.SPANNABLE);
        }else{
            viewHolder.triicon.setVisibility(View.GONE);
            viewHolder.likeLayout.setVisibility(View.GONE);
        }
        viewHolder.pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopwindow(v);
            }
        });
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
    private void showpopwindow(View v){
        View view =LayoutInflater.from(context).inflate(R.layout.popwindow,null,false);
        view.measure(0,0);
        PopupWindow popupWindow=new PopupWindow(view,view.getMeasuredWidth(),80,true);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        int[] location =new int[2];
        v.getLocationInWindow(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY,location[0]-popupWindow.getWidth(),location[1]-15);
    }
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private class ViewHolder{
        TextView txv;
        TextView likeTxv;
        ImageView triicon;
        ImageView pl;
        LinearLayout likeLayout;
        RecyclerView recyclerView;
    }
}
