package com.example.debug.recyclerview.adapter;
/*
 * @author xy
 * @emil 384813636@qq.com
 * create at 2018/6/14
 * description:
 */

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.UserViewInfo;

public class MyBaseQuickAdapter extends BaseQuickAdapter<UserViewInfo,BaseViewHolder> {
    public static final String TAG = "MyBaseQuickAdapter";
    private Context context;
    private int size;
    public MyBaseQuickAdapter(Context context,int size) {
        super(R.layout.recycler_item);
        this.context=context;
        this.size=size;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserViewInfo item) {
        final ImageView thumbView = helper.getView(R.id.images);
        Glide.with(context)
                .load(item.getUrl())
                .error(R.mipmap.ic_empty_photo)
                .into(thumbView);

        helper.getView(R.id.images).setTag(R.id.images,item.getUrl());
        if(size==1){
            thumbView.setScaleType(ImageView.ScaleType.FIT_START);
        }else{
            thumbView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}

