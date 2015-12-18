package com.lizhidu.androidapparchitecturedesign;

import android.app.Application;
import android.content.Context;

import org.xutils.x;


public class MyApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
