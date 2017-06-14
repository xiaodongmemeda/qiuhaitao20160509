package test.bwie.com.qiuhaitao20160509.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：application类
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
