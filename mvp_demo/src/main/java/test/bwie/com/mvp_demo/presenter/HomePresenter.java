package test.bwie.com.mvp_demo.presenter;

import android.util.Log;

import java.util.HashMap;

import test.bwie.com.mvp_demo.model.home.HomeBean;
import test.bwie.com.mvp_demo.model.home.HttpUtils;
import test.bwie.com.mvp_demo.view.iview.IHomeView;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    String uri = "http://api.expoon.com/AppNews/getNewsList/";
    HashMap<String,String> hashMap = new HashMap();
    public <T>void getDataFromServer(){
        HttpUtils.getDataRetrofitTest(uri, new HttpUtils.CallBackP<HomeBean>() {
            @Override
            public void callBack(HomeBean t) {
                Log.d("homebean",""+t.getData().get(0).getNews_title());
                getView().callBackStr(t);
            }
        });
        /*HttpUtils.getData(uri, hashMap, cla, new HttpUtils.CallBackP<T>() {
            @Override
            public void callBack(T t) {
                getView().callBackStr(t);
            }
        });*/

    }



}
