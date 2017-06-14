package test.bwie.com.mvp_demo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.mvp_demo.R;
import test.bwie.com.mvp_demo.model.home.HomeBean;
import test.bwie.com.mvp_demo.presenter.VideoPresenter;
import test.bwie.com.mvp_demo.view.iview.OnRecyclerViewItemClickListener;

/**
 * Created by ${仇海涛} on 2017/5/17.
 * 类的用途 ：
 */

public class Video_ada extends RecyclerView.Adapter<Video_ada.MyViewHolder>{
    //上下文
    private Context context;
    //数据集合
    private List<HomeBean.ResultBean.DataBean> list = new ArrayList<>();
    //p层变量
    private VideoPresenter mVideoPresenter;
    //构造器用来传递上下文
    public Video_ada(Context context) {
        this.context = context;
    }

    //监听接口变量
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    //床底接口变量的方法
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //将布局中条目的view传递给自定义的MyViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载自定义的view
        final View view = View.inflate(context,R.layout.video_item,null);
        //实例化在自定义的MyViewHolder 并将view传递进去
        final MyViewHolder myViewHolder = new MyViewHolder(view);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到接口回调传递的
                int position = myViewHolder.getLayoutPosition();
                //调用接口的方法传递参数 实现接口回调
                mOnItemClickListener.onItemClick(v,position);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //得到接口回调传递的
                int position = myViewHolder.getLayoutPosition();
                //调用接口的方法传递参数 实现接口回调
                mOnItemClickListener.onItemLongClick(view,position);
                return false;
            }
        });
        return myViewHolder;
    }
    //
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mVideoPresenter.setImg(holder.imageView,list.get(position).getThumbnail_pic_s());
        holder.textView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size()  ;
    }
    public void setData(List<HomeBean.ResultBean.DataBean> data){
        if (data!=null){
            this.list = data;
        }
    }

    public void setVideoPresenter(VideoPresenter homePresenter) {
        this.mVideoPresenter = homePresenter;
    }





    //自定义的ViewHoder，持有item的所有控件
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View view) {
            super (view);
            imageView = (ImageView) view.findViewById(R.id.video_item_img);
            textView = (TextView) view.findViewById(R.id.video_item_text);
        }
    }
}
