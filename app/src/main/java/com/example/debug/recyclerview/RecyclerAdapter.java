package com.example.debug.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder>{
    private Context context;
    private List<String> urlList;
    public RecyclerAdapter(Context context, List<String> urlList){
        this.context=context;
        this.urlList=urlList;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context,ImageShower.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",urlList.get(position).toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
                // Toast.makeText(v.getContext(),"url"+urlList.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        if(urlList.size()==1){
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(urlList.get(position)).override(600, 600).into(holder.imageView);
        }else{
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(urlList.get(position)).override(300, 300).into(holder.imageView);
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
