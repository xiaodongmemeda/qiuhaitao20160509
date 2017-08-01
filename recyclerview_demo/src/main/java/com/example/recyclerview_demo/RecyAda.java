package com.example.recyclerview_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/27.
 */

public class RecyAda extends RecyclerView.Adapter<RecyAda.MyViewHolder> {
    List<String> list = new ArrayList<>();
    Context context;
    private RecyOnClick onClick;
    public RecyAda(Context context) {
        this.context = context;
    }
    public void setOnClick(RecyOnClick recyOnClick){
        this.onClick = recyOnClick;
    }
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item,null);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                onClick.onClick(v,position);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getLayoutPosition();
                onClick.onLongClick(v,position);
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
    public void setData(List<String> data){
        if (data!=null){
            this.list = data;
        }
        notifyDataSetChanged();
    }
}
