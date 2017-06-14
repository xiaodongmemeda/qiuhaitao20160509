package test.bwei.com.eventbus_demo.presenter;

import com.google.gson.Gson;

import test.bwei.com.eventbus_demo.model.HttpUtils;
import test.bwei.com.eventbus_demo.model.UriUtils;
import test.bwei.com.eventbus_demo.view.iview.Second_Iview;

/**
 * Created by ${仇海涛} on 2017/5/23.
 * 类的用途 ：
 */

public class Second_presenter extends MPresenter<Second_Iview>{


    public<T> void getDataFromServer(final Class<T> cla){
        HttpUtils.getdata(UriUtils.uri1, new HttpUtils.CallBackData<String>() {

            @Override
            public void callBack(String s) {
                Gson gson = new Gson();
                T t = gson.fromJson(s, cla);
                getView().callBackDataFromP(t);
            }
        });

    }


}
