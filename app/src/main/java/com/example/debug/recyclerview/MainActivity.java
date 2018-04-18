package com.example.debug.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ListBean> listData = new ArrayList<>();
    private ListView listView;
    private ListAdapter listAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        listView = findViewById(R.id.list_view);
        ScrollView scrollView=findViewById(R.id.scrollview);
        scrollView.smoothScrollTo(0,0);
        listAdapter = new ListAdapter(this, listData);
        listView.setAdapter(listAdapter);
    }

    public void setData() {
        ListBean listBean0 = new ListBean();
        listBean0.setnickName("nickName0");
        listBean0.setsb().append(",");
        listData.add(listBean0);

        ListBean listBean1 = new ListBean();
        listBean1.getList().add(mUrls[0]);
        listBean1.setnickName("nickName1");
        listBean1.setsb().append(",");
        listData.add(listBean1);

        ListBean listBean2 = new ListBean();
        for (int i = 0; i < 2; i++) {
            listBean2.getList().add(mUrls[i]);
            listBean2.setsb().append("好友" + i + ",");
        }
        listBean2.setnickName("nickName2");
        listData.add(listBean2);

        ListBean listBean3 = new ListBean();
        for (int i = 0; i < 3; i++) {
            listBean3.getList().add(mUrls[i]);
            listBean3.setsb().append("好友" + i + ",");
        }
        listBean3.setnickName("nickName3");
        listData.add(listBean3);

        ListBean listBean4 = new ListBean();
        StringBuilder sb4 = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            listBean4.getList().add(mUrls[i]);
            listBean4.setsb().append("好友" + i + ",");
        }
        listBean4.setnickName("nickName4");
        listData.add(listBean4);

        ListBean listBean5 = new ListBean();
        for (int i = 0; i < 5; i++) {
            listBean5.getList().add(mUrls[i]);
            listBean5.setsb().append("好友" + i + ",");
        }
        listBean5.setnickName("nickName5");
        listData.add(listBean5);

        ListBean listBean6 = new ListBean();
        for (int i = 0; i < 6; i++) {
            listBean6.getList().add(mUrls[i]);
            listBean6.setsb().append("好友" + i + ",");
        }
        listBean6.setnickName("nickName6");
        listData.add(listBean6);
    }
}
