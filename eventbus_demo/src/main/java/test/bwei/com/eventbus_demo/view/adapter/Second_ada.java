package test.bwei.com.eventbus_demo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwei.com.eventbus_demo.R;
import test.bwei.com.eventbus_demo.model.SecondBean;
import test.bwei.com.eventbus_demo.presenter.Second_presenter;
import test.bwei.com.eventbus_demo.view.iview.OnRecyclerViewItemClickListener;

/**
 * Created by ${仇海涛} on 2017/5/17.
 * 类的用途 ：
 */

public class Second_ada extends RecyclerView.Adapter<Second_ada.MyViewHolder>{
    //上下文
    private Context context;
    //数据集合
    private List<SecondBean.ResultBean.DataBean> list = new ArrayList<>();
    //p层变量
    private Second_presenter presenter;
    //构造器用来传递上下文
    public Second_ada(Context context) {
        this.context = context;
    }

    //监听接口变量
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    //床底接口变量的方法
    public void setClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //将布局中条目的view传递给自定义的MyViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载自定义的view
        final View view = View.inflate(context, R.layout.second_item,null);
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

        holder.textView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size()  ;
    }
    public void setData(List<SecondBean.ResultBean.DataBean> data){
        if (data!=null){
            this.list = data;
        }
    }

    public void setVideoPresenter(Second_presenter presenter) {
        this.presenter = presenter;
    }





    //自定义的ViewHoder，持有item的所有控件
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View view) {
            super (view);

            textView = (TextView) view.findViewById(R.id.video_item_text);
        }
    }
}
