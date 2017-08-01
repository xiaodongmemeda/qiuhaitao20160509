package com.example.recyclerview_demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 仇海涛 on 2017/8/1.
 * class ：
 * content ：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        Fresco.initialize(this);
    }
}
