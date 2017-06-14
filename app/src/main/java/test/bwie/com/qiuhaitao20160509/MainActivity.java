package test.bwie.com.qiuhaitao20160509;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.qiuhaitao20160509.adapter.VpAda;
import test.bwie.com.qiuhaitao20160509.utils.UrlUtils;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：主页面加载类
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //适配数据
        initData();
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        List<String> lists = new ArrayList<>();

        list.add("热点");
        list.add("娱乐");
        list.add("体育");
        list.add("科技");
        list.add("财经");
        list.add("军事");
        list.add("八卦");
        list.add("国内");
        list.add("国外");
        list.add("社会");
        list.add("国际");


       /* SharedPreferences sharedPreferences = getSharedPreferences("ss",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.putString("s","s");*/


        lists.add(UrlUtils.url1);
        lists.add(UrlUtils.url2);
        lists.add(UrlUtils.url3);
        lists.add(UrlUtils.url4);
        lists.add(UrlUtils.url5);
        lists.add(UrlUtils.url6);
        lists.add(UrlUtils.url7);
        lists.add(UrlUtils.url8);
        lists.add(UrlUtils.url9);
        lists.add(UrlUtils.url10);
        lists.add(UrlUtils.url11);

        VpAda vpAda = new VpAda(getSupportFragmentManager(),list,lists);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
        tabLayout.setTabsFromPagerAdapter(vpAda);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(vpAda);
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tb);
        viewPager = (ViewPager) findViewById(R.id.vp);
    }
}
