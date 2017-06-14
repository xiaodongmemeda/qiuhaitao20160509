package test.bwie.com.qiuhaitao20160509.utils;

import android.content.Context;
import android.widget.ListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import test.bwie.com.qiuhaitao20160509.adapter.LvAda;
import test.bwie.com.qiuhaitao20160509.bean.Bean;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：xutils解析数据类
 */

public class JsonUtils {
    Context context;
    ListView listView;

    public JsonUtils(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    public void getJson(String url){
        RequestParams params = new RequestParams();
        params.setUri(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Bean bean = GsonUtils.getBean(result, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                LvAda lvAda = new LvAda(context,data);
                listView.setAdapter(lvAda);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
