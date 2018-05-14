package com.example.debug.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.debug.recyclerview.adapter.ListAdapter;
import com.example.debug.recyclerview.bean.Comment;
import com.example.debug.recyclerview.bean.ListBean;
import com.example.debug.recyclerview.bean.MessageEvent;
import com.example.debug.recyclerview.utils.Commentfun;
import com.example.debug.recyclerview.view.PullToRefreshLayout;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ListBean> listData = new ArrayList<>();
    private ListView listView;
    private ListAdapter listAdapter;
    private PullToRefreshLayout layout;
    private ImageView headimg;
    private String[] mUrls = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523940434538&di" +
                    "=4f4fe0e88d3daf3638c34fc2ea34fbbc&imgtype=jpg&src=http%3A%2F%2Fimg1.imgtn" +
                    ".bdimg.com%2Fit%2Fu%3D3649436761%2C627241649%26fm%3D214%26gp%3D0.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115" +
                    "/ca1349540923dd54e54f7aedd609b3de9c824873.jpg"};
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onmessageEvent(MessageEvent event){
        if(event.msg=="zan") {
            listData.get(event.position).setsb().append("赞"+",");
            listAdapter.notifyDataSetChanged();
        }else {
            Commentfun.inputComment(this, event.listView, event.v, listData, event.position, new Commentfun.InputCommentListener() {


                @Override
                public void onCommitComment() {
                    listAdapter.notifyDataSetChanged();
                }
            });
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        listView = findViewById(R.id.list_view);
        layout=(PullToRefreshLayout) findViewById(R.id.refresh_layout);
        //ScrollView scrollView=findViewById(R.id.scrollview);
        //scrollView.smoothScrollTo(0,50);
        listAdapter = new ListAdapter(MainActivity.this, listData);
        listView.setAdapter(listAdapter);
        View head= LayoutInflater.from(this).inflate(R.layout.head_view_layout,null);
        listView.addHeaderView(head,null,true);
        listView.setHeaderDividersEnabled(false);
        layout.setListener(new PullToRefreshLayout.RefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.stopRefresh();
                        Toast.makeText(MainActivity.this,"刷新完成",Toast.LENGTH_SHORT).show();
                    }
                },1000);
            }
        });
        headimg=findViewById(R.id.headimg);
        headimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"hhhhhhead",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setData() {
        ListBean listBean0 = new ListBean();
        listBean0.setnickName("nickName0");
        listBean0.setlikeState(false);
       // listBean0.setsb().append(",");
        listData.add(listBean0);
        ListBean listBean1 = new ListBean();
        listBean1.getList().add(mUrls[0]);
        listBean1.setnickName("nickName1");
        listBean1.setlikeState(false);
        listBean1.getCommentList().add(new Comment("user1",2,"comment1",""));
        //listBean1.setsb().append(",") ;
        listData.add(listBean1);
        ListBean listBean2 = new ListBean();
        listBean2.setlikeState(true);
        for (int i = 0; i < 2; i++) {
            listBean2.getCommentList().add(new Comment("user"+i,2+i,"comment"+i,"he"));
            listBean2.getList().add(mUrls[i]);
           // listBean2.setsb().append("好友" + i + ",");
            listBean2.getlikelist().add("好友"+i);
        }
        listBean2.setnickName("nickName2");
        listData.add(listBean2);
        ListBean listBean3 = new ListBean();
        listBean3.setlikeState(true);
        for (int i = 0; i < 3; i++) {
            listBean3.getCommentList().add(new Comment("user"+i,2+i,"comment"+i,"he"));
            listBean3.getList().add(mUrls[i]);
            //listBean3.setsb().append("好友" + i + ",");
            listBean3.getlikelist().add("好友"+i);
        }
        listBean3.setnickName("nickName3");
        listData.add(listBean3);

        ListBean listBean4 = new ListBean();
        listBean4.setlikeState(true);
        //StringBuilder sb4 = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            listBean4.getCommentList().add(new Comment("user"+i,2+i,"commentas"+i,"he"));
            listBean4.getList().add(mUrls[i]);
            listBean4.getlikelist().add("好友"+i);
            //listBean4.setsb().append("好友" + i + ",");
        }
        listBean4.setnickName("nickName4");
        listData.add(listBean4);
        ListBean listBean5 = new ListBean();
        listBean5.setlikeState(false);
        for (int i = 0; i < 5; i++) {
            listBean5.getCommentList().add(new Comment("user"+i,2+i,"comment"+i,"he"));
            listBean5.getList().add(mUrls[i]);
            listBean5.getlikelist().add("好友"+i);
            //listBean5.setsb().append("好友" + i + ",");
        }
        listBean5.setnickName("nickName5");
        listData.add(listBean5);

        ListBean listBean6 = new ListBean();
        listBean6.setlikeState(true);
        for (int i = 0; i < 6; i++) {
            listBean6.getCommentList().add(new Comment("user"+i,2+i,"comment"+i,"he"));
            listBean6.getList().add(mUrls[i]);
            listBean6.getlikelist().add("好友"+i);
            //listBean6.setsb().append("好友" + i + ",");
        }
        listBean6.setnickName("nickName6");
        listData.add(listBean6);
    }
}
