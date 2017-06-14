package test.qht.com.uploading_demo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by ${仇海涛} on 2017/5/16.
 * 类的用途 ：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
