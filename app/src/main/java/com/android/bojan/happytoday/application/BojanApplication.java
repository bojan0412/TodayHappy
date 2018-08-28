package com.android.bojan.happytoday.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Create by bojan
 * on 2018/8/28
 */
public class BojanApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
    }
}
