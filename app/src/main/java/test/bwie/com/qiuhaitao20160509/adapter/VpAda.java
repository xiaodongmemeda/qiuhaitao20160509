package test.bwie.com.qiuhaitao20160509.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import test.bwie.com.qiuhaitao20160509.fragment.Frag;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：viewpager适配器类
 */

public class VpAda extends FragmentPagerAdapter {
    List<String> list;
    List<String> lists;

    public VpAda(FragmentManager fm, List<String> list,List<String> lists) {
        super(fm);
        this.list = list;
        this.lists = lists;
    }



    @Override
    public Fragment getItem(int position) {
        Frag frag = new Frag();
        frag.url = lists.get(position);
        return frag;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
