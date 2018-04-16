package com.example.debug.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        }else {
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3, OrientationHelper.VERTICAL, false));
        }
        return convertView;
    }
    private class ViewHolder{
        TextView txv;
        RecyclerView recyclerView;
    }
}
