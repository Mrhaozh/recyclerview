package com.example.debug.recyclerview.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;


public class ViewpagerAdapter extends PagerAdapter{
    private ArrayList<ImageView> imagelist;
    private Context context;
    public ViewpagerAdapter(Context context,ArrayList<ImageView> imagelist){
        this.context=context.getApplicationContext();
        this.imagelist=imagelist;
    }
    @Override
    public int getCount() {
        return imagelist.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position,(View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView iv=imagelist.get(position);
        container.addView(iv);
        //return super.instantiateItem(container, position);
        return iv;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
