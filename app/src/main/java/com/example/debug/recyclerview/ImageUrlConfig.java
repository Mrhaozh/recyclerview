package com.example.debug.recyclerview;
/*
 * @author xy
 * @emil 384813636@qq.com
 * create at 2018/6/14
 * description:
 */

import java.util.ArrayList;
import java.util.List;

public final class ImageUrlConfig {

    private static List<String> sUrls = new ArrayList<>();

    public static List<String> getUrls() {
        sUrls.clear();
        sUrls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");
        sUrls.add("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg");
        sUrls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");
        sUrls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
        sUrls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
        sUrls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
        return sUrls;
    }
}