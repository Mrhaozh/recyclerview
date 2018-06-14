package com.example.debug.recyclerview.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.debug.recyclerview.ImageUrlConfig;
import com.example.debug.recyclerview.UserViewInfo;
import com.example.debug.recyclerview.utils.AnimationTools;
import com.example.debug.recyclerview.utils.CenterAlignImageSpan;
import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.bean.ListBean;
import com.example.debug.recyclerview.bean.MessageEvent;
import com.example.debug.recyclerview.view.ListViewForScrollView;
import com.previewlibrary.GPreviewBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter{
    private Context context;
    private Activity activity;
    private ArrayList<ListBean> listData;
    //private GridLayoutManager mGridLayoutManager;
    //private ArrayList<UserViewInfo> mThumbViewInfoList = new ArrayList<>();
    //private  Commentfun commentfun=new Commentfun();
    // private ListView listView;
    // private LinearLayout heightTag;
    public ListAdapter(Context context, ArrayList<ListBean> listData,Activity activity){
        this.context=context.getApplicationContext();
        this.listData=listData;
        this.activity=activity;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //listView =(ListView)parent;
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.txv=convertView.findViewById(R.id.nickName);
            viewHolder.likeTxv=convertView.findViewById(R.id.likeTxv);
            viewHolder.triicon=convertView.findViewById(R.id.triicon);
            viewHolder.likeLayout=convertView.findViewById(R.id.likeLayout);
            viewHolder.recyclerView=convertView.findViewById(R.id.recycler_view);
            viewHolder.pl=convertView.findViewById(R.id.pl);
            viewHolder.commentlistview=convertView.findViewById(R.id.commentlist);
            viewHolder.heightTag=convertView.findViewById(R.id.heightTag);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.txv.setText(listData.get(position).getnickName());
       // RecyclerAdapter recyclerAdapter=new RecyclerAdapter(context,(ArrayList<String>)listData.get(position).getList());
       // viewHolder.recyclerView.setAdapter(recyclerAdapter);
        /*List<String> urls = ImageUrlConfig.getUrls();
        for (int i = 0; i < 5; i++) {
            mThumbViewInfoList.add(new UserViewInfo(urls.get(i)));
        }*/
        final GridLayoutManager mGridLayoutManager;
        if(listData.get(position).getList().size()==1){
            ViewGroup.LayoutParams params=viewHolder.recyclerView.getLayoutParams();
//            params.width=160;
            int getwidth=dp2px(context,270);
            params.width=getwidth;
            viewHolder.recyclerView.setLayoutParams(params);
            mGridLayoutManager = new GridLayoutManager(context,1);
            viewHolder.recyclerView.setLayoutManager(mGridLayoutManager);
        }else if(listData.get(position).getList().size()==4){
            ViewGroup.LayoutParams params=viewHolder.recyclerView.getLayoutParams();
//            params.width=160;
            int getwidth=dp2px(context,180);
            params.width=getwidth;
            viewHolder.recyclerView.setLayoutParams(params);
            mGridLayoutManager = new GridLayoutManager(context,2);
            viewHolder.recyclerView.setLayoutManager(mGridLayoutManager);
        }else{
            ViewGroup.LayoutParams params=viewHolder.recyclerView.getLayoutParams();
//            params.width=160;
            int getwidth=dp2px(context,270);
            params.width=getwidth;
            viewHolder.recyclerView.setLayoutParams(params);
            mGridLayoutManager = new GridLayoutManager(context,3);
            viewHolder.recyclerView.setLayoutManager(mGridLayoutManager);
        }
        viewHolder.recyclerView.setHasFixedSize(true);
        MyBaseQuickAdapter adapter=new MyBaseQuickAdapter(context,listData.get(position).getList().size());
        //mThumbViewInfoList=listData.get(position).getList();
        adapter.addData(listData.get(position).getList());
        viewHolder.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int itemposition) {
                computeBoundsBackward(mGridLayoutManager.findFirstVisibleItemPosition(),position,mGridLayoutManager);
                GPreviewBuilder.from(activity)
                        .setData(listData.get(position).getList())
                        .setCurrentIndex(itemposition)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start();
            }
        });
        /*
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
        }*/
        if(listData.get(position).getlikelist().size()>0){
            viewHolder.triicon.setVisibility(View.VISIBLE);
            viewHolder.likeLayout.setVisibility(View.VISIBLE);
            viewHolder.likeTxv.setMovementMethod(LinkMovementMethod.getInstance());
            viewHolder.likeTxv.setText(addClickPart(listData.get(position).getlikelist()),TextView.BufferType.SPANNABLE);
        }else{
            viewHolder.triicon.setVisibility(View.GONE);
            viewHolder.likeLayout.setVisibility(View.GONE);
        }
        viewHolder.pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopwindow(v,(ListView)parent,position,viewHolder.heightTag);
            }
        });

        //commentfun.setCommentList(context,listData,(ListView)parent,viewHolder.commentlistview,position);
         CommentListAdapter commentListAdapter =new CommentListAdapter(context,listData.get(position).getCommentList(),(ListView) parent,position);
         viewHolder.commentlistview.setAdapter(commentListAdapter);
        return convertView;
    }
    private SpannableStringBuilder addClickPart(final List<String> list){
        CenterAlignImageSpan imgspan =new CenterAlignImageSpan(context,R.drawable.heart);
        SpannableString spanStr=new SpannableString("p");
        spanStr.setSpan(imgspan,0,1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        SpannableStringBuilder ssb =new SpannableStringBuilder(spanStr);
        //String likeUsers=listToString(list);
       // ssb.append(likeUsers);
          //String[] likeUsers = str.split(",");
            if (list.size() > 0) {
                for (int i = 0; i < list.size();i++) {
                    final String name;
                    if(i==list.size()-1){
                        name = list.get(i).toString();
                    }else {
                        name = list.get(i).toString() + ",";
                    }
                    SpannableString spannableString=new SpannableString(name);
                    final int start = list.indexOf(name) + spanStr.length();
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(Color.BLUE);
                            ds.setUnderlineText(false);
                        }
                    }, 0, name.length(), 0);
                    ssb.append(spannableString);
                }

            }

        return ssb;
    }
    private void showpopwindow(View v, final ListView parent,final int position, LinearLayout heightTag){
        //final ImageView plbutton=v.findViewById(R.id.pl);
        final LinearLayout height=heightTag;
        View view =LayoutInflater.from(context).inflate(R.layout.popwindow,parent,false);
        TextView textView=view.findViewById(R.id.popwindowlike);
        final ImageView imageView = view.findViewById(R.id.popwindowlikeimg);
        if(listData.get(position).getlikeState()){
            textView.setText("取消");
        }else{
            textView.setText("赞");
        }
        view.measure(0,0);
        final PopupWindow popupWindow=new PopupWindow(view,view.getMeasuredWidth(),100,true);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //popupWindow.setBackgroundDrawable(new PaintDrawable(R.drawable.corner));
        int[] location =new int[2];
        v.getLocationInWindow(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY,location[0]-popupWindow.getWidth(),location[1]-15);
        LinearLayout zan = view.findViewById(R.id.zan);
        LinearLayout pinglun=view.findViewById(R.id.pinglun);
        zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationTools.scale(imageView);
                if(listData.get(position).getlikeState()) {
                    listData.get(position).getlikelist().remove(listData.get(position).getlikelist().size()-1);
                }else{
                    listData.get(position).getlikelist().add("赞");
                }
                listData.get(position).setlikeState(!listData.get(position).getlikeState());
                notifyDataSetChanged();
                popupWindow.dismiss();
                //EventBus.getDefault().post(new MessageEvent("zan",parent,height,position));
               // Toast.makeText(context,"aaa", Toast.LENGTH_SHORT).show();
            }
        });
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                EventBus.getDefault().post(new MessageEvent(parent,height,position));
            }
        });
    }
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private String listToString(List<String> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }
    private class ViewHolder{
        TextView txv;
        TextView likeTxv;
        ImageView triicon;
        ImageView pl;
        LinearLayout likeLayout;
        RecyclerView recyclerView;
        ListViewForScrollView commentlistview;
        LinearLayout heightTag;
    }
    private void computeBoundsBackward(int firstCompletelyVisiblePos,int position,GridLayoutManager mGridLayoutManager) {
        for (int i = firstCompletelyVisiblePos;i < listData.get(position).getList().size(); i++) {
            View itemView = mGridLayoutManager.findViewByPosition(i);
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.images);
                thumbView.getGlobalVisibleRect(bounds);
            }
            listData.get(position).getList().get(i).setBounds(bounds);
        }
    }
}
