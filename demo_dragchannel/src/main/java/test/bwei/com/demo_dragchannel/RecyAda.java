
package test.bwei.com.demo_dragchannel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${仇海涛} on 2017/5/26.
 * 类的用途 ：
 */

public class RecyAda extends RecyclerView.Adapter<RecyAda.MyViewHolder> {
    Context context;
    List<ItemBean> list = new ArrayList<>();
    private RecyclerOnClick recyclerOnClick;


    public RecyAda(Context context) {
        this.context = context;
    }
    public void setOnClick(RecyclerOnClick recyclerOnClick){
        this.recyclerOnClick = recyclerOnClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item,null);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = myViewHolder.getLayoutPosition();
                recyclerOnClick.onItemClick(view,position);
            }
        });
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<ItemBean> data) {
        if (data!=null){
            this.list = data;
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
