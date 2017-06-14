package test.bwie.com.mvp_demo.presenter;

import java.util.HashMap;

import test.bwie.com.mvp_demo.model.utils.HttpUtils;
import test.bwie.com.mvp_demo.model.utils.UriUtils;
import test.bwie.com.mvp_demo.view.iview.IVideoView;

/**
 * Created by ${仇海涛} on 2017/5/10.
 * 类的用途 ：
 */

public class VideoPresenter extends BasePresenter<IVideoView> {

    private HashMap<String,String> hashMap = new HashMap<>();

    public <T>void getVideoDataFromServer(Class<T> cla) {

        HttpUtils.getDataFromM(UriUtils.uri, hashMap, new HttpUtils.CallBackData<T>() {
            @Override
            public void callBackDataToM(T o) {
                getIview().callBackStr(o);
            }
        },cla);

    }
}
