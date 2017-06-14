package test.bwie.com.qiuhaitao20160509.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import test.bwie.com.qiuhaitao20160509.R;
import test.bwie.com.qiuhaitao20160509.bean.Bean;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：listview适配器类
 */

public class LvAda extends BaseAdapter {
    Context context;
    List<Bean.DataBean> list;

    public LvAda(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    ImageOptions options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.ic_launcher).build();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null){
            holder = new Holder();
            view = View.inflate(context, R.layout.item,null);
            holder.imageView = (ImageView) view.findViewById(R.id.item_img);
            holder.textView1 = (TextView) view.findViewById(R.id.item_text1);
            holder.textView2 = (TextView) view.findViewById(R.id.item_text2);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        x.image().bind(holder.imageView,list.get(i).getPic_url(),options);
        holder.textView1.setText(list.get(i).getNews_title());
        holder.textView2.setText(list.get(i).getNews_summary());
        return view;
    }
    class Holder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
