package test.bwie.com.mvp_demo.view.iview;

import android.view.View;

/**
 * Created by ${仇海涛} on 2017/5/17.
 * 类的用途 ：
 */
//自定义监听事件
public interface OnRecyclerViewItemClickListener {
    void onItemClick(View view,int position);
    void onItemLongClick(View view,int position);
}
