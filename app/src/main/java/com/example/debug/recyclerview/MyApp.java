package com.example.debug.recyclerview;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
    public static  MyApp getContext(){
        return (MyApp) context;
    }
}
