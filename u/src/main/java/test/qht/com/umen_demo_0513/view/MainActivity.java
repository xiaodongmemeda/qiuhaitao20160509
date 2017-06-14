package test.qht.com.umen_demo_0513.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import test.qht.com.umen_demo_0513.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button hello;
    private Button world;
    private UMShareListener mUmShareListener = new UMShareListener() {

        //分享开始监听
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        //分享成功监听
        @Override
        public void onResult(SHARE_MEDIA share_media) {

        }

        //分享失败监听
        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        //分享取消监听
        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };


    private UMAuthListener umAuthListener = new UMAuthListener() {

        //授权开始回调方法
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        //授权成功回调方法
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            // 获取 data 中的 字段值
            // data 是 授权成功后，返回的数据资源对象，我们通过get方法获取其中的数据。
            // 如：获取QQ昵称
            String qq_Name = data.get("screen_name");

            // 注意：
            // 1. 该 Map集合中的字段都是固定的，所以我们需要照表查询。
            //2. 如果 该 字段中含有 非法或奇异字符时，会出现 乱码现象，注意。

        }

        //授权错误回调方法
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        }

        //授权取消回调方法
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };
    private UMShareConfig shareConfig;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        hello = (Button) findViewById(R.id.hello);
        world = (Button) findViewById(R.id.world);


        hello.setOnClickListener(this);
        world.setOnClickListener(this);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

    }
    private static final int TYPE_ITEM = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hello:
                shareConfig = new UMShareConfig();
                shareConfig.isNeedAuthOnGetUserInfo(true);
                shareConfig.isOpenShareEditActivity(true);
                UMShareAPI.get(this).setShareConfig(shareConfig);


                // SSO 授权：
                shareConfig.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
                //Web授权：
                shareConfig.setSinaAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);

                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.world:

                shareConfig.isNeedAuthOnGetUserInfo(true);
                shareConfig.isOpenShareEditActivity(true);
                UMShareAPI.get(this).setShareConfig(shareConfig);


                // SSO 授权：
                shareConfig.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
                //Web授权：
                shareConfig.setSinaAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);

                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                new ShareAction(MainActivity.this)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                        .setCallback(mUmShareListener)
                        .open();

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
