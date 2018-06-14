package com.example.debug.recyclerview.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.adapter.ViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageShower extends Activity{
    private ImageView imageView;
    private ViewPager viewPager;
    private ArrayList<String> list;
    private List<ImageView> imagelist=new ArrayList<ImageView>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);
        //Bundle bundle=getIntent().getExtras();
        //String url=bundle.getString("url");
        //imageView=findViewById(R.id.showBig);
        //Glide.with(this).load(url).into(imageView);
        viewPager=findViewById(R.id.viewpager);
        Bundle bundle=getIntent().getExtras();
        list=bundle.getStringArrayList("url");
        for (int i = 0; i < list.size(); i++) {
                ImageView imageView = new ImageView(this);
           // Glide.with(context).load(urlList.get(position)).override(600, 600).into(holder.imageView);
                Glide.with(this).load(list.get(i)).override(600,600).into(imageView);
                imagelist.add(imageView);
            }
        ViewpagerAdapter viewpagerAdapter=new ViewpagerAdapter(this,(ArrayList<ImageView>) imagelist);
        viewPager.setAdapter(viewpagerAdapter);
//        final ImageLoadingDialog dialog =new ImageLoadingDialog(this);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//            }
//        },1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}
