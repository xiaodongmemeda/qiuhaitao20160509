package test.qht.com.umen_demo;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.smssdk.SMSSDK;

/**
 * Created by ${仇海涛} on 2017/5/12.
 * 类的用途 ：
 */

public class MyApp extends Application {
    {
        PlatformConfig.setQQZone("1106086767", "pC2wfXv6quTcZgPg");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        SMSSDK.initSDK(this, "1cf90ed6a74cd", "61737dfe855cbae4edc42ec4413d384d");
    }
}
