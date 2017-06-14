package test.qht.com.umen_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button share;
    private CircleImageView img;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            switch (i) {
                case ACTION_AUTHORIZE:
                    Toast.makeText(MainActivity.this, "登陆成功了", Toast.LENGTH_LONG).show();
                    UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, platforms.get(0).mPlatform, authListener);
                    break;
                case ACTION_GET_PROFILE:
                    Glide.with(MainActivity.this).load(map.get("iconurl")).into(img);
                    text1.setText(map.get("name"));
                   /* ImageLoader.getInstance().displayImage(map.get("iconurl"), mImageView);
                    mTextView.setText(map.get("name"));*/
                    break;
                case ACTION_DELETE:
                    Toast.makeText(MainActivity.this, "取消授权", Toast.LENGTH_LONG).show();
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable t) {
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private TextView text1;
    private Button mob;

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlatforms();
        initView();
    }

    private void initView() {
        login = (Button) findViewById(R.id.login);
        share = (Button) findViewById(R.id.share);
        img = (CircleImageView) findViewById(R.id.img);
        text1 = (TextView) findViewById(R.id.text1);

        login.setOnClickListener(this);
        share.setOnClickListener(this);

        mob = (Button) findViewById(R.id.mob);
        mob.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                UMShareAPI.get(this).isAuthorize(MainActivity.this, platforms.get(0).mPlatform);
                UMShareAPI.get(this).doOauthVerify(MainActivity.this, platforms.get(0).mPlatform, authListener);
                break;
            case R.id.share:

                UMWeb web = new UMWeb("https://www.baidu.com/");
                web.setTitle("This is music title\");//标题\n" +
                        "                //web.setThumb(thumb);  //缩略图\n" +
                        "                web.setDescription(\"my description");//描述


                new ShareAction(MainActivity.this)
                        .withText("hello")
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();

                break;
            case R.id.mob:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

// 提交用户信息（此方法可以不调用）
                            //registerUser(country, phone);
                        }
                    }
                });
                registerPage.show(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
