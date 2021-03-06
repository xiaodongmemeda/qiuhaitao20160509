package test.bwie.com.mvp_demo.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.mvp_demo.R;
import test.bwie.com.mvp_demo.model.home.HomeBean;
import test.bwie.com.mvp_demo.presenter.BasePresenter;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：
 */

public class HomeAdapter extends BaseAdapter{
    Context context;
    List<HomeBean.ResultBean.DataBean> list = new ArrayList<>();
    private BasePresenter homePresenter;


    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HomeBean.ResultBean.DataBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.home_item,null);
            holder.imageView = (ImageView) view.findViewById(R.id.item_img);
            holder.textView = (TextView) view.findViewById(R.id.item_text1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        homePresenter.setImg(holder.imageView,getItem(i).getThumbnail_pic_s());
        holder.textView.setText(getItem(i).getTitle());
        return view;
    }



    public void setData(List<HomeBean.ResultBean.DataBean> data) {
        if (data!=null){
            list.addAll(data);
        }

    }

    public void setHomePresenter(BasePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
