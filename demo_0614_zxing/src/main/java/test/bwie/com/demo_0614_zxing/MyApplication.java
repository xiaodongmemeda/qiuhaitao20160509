package test.bwie.com.demo_0614_zxing;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by ASUS on 2017/6/14.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);

    }
}
