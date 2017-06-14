package test.qht.com.umen_demo_0513.application;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by ${仇海涛} on 2017/5/13.
 * 类的用途 ：
 */

public class MyApplication extends Application {
    {
        PlatformConfig.setQQZone("1106086767","pC2wfXv6quTcZgPg");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
    }
}
