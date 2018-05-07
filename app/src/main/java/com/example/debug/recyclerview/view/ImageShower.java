package com.example.debug.recyclerview.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.debug.recyclerview.R;

public class ImageShower extends Activity{
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
        imageView=findViewById(R.id.showBig);
        Glide.with(this).load(url).into(imageView);
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
