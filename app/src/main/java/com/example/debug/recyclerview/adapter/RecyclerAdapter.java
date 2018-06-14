package com.example.debug.recyclerview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.debug.recyclerview.view.ImageShower;
import com.example.debug.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class  RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder>{
    private Context context;
    private ArrayList<String> urlList;
    public RecyclerAdapter(Context context, ArrayList<String> urlList){
        this.context=context.getApplicationContext();
        this.urlList=urlList;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, final int position) {
  /*      holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context,ImageShower.class);
                Bundle bundle=new Bundle();
                bundle.putStringArrayList("url",urlList);
                //bundle.putSerializable("url",(ArrayList<String>)urlList);
                intent.putExtras(bundle);

                context.startActivity(intent);
                // Toast.makeText(v.getContext(),"url"+urlList.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });*/

        if(urlList.size()==1){
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_START);
            Glide.with(context).load(urlList.get(position)).override(600, 600).into(holder.imageView);
        }else{
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(urlList.get(position)).override(50, 50).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }
    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public RecycleViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.images);
        }
    }
}
