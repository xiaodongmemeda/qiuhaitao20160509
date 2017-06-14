package test.bwie.com.mvp_demo.presenter;

import java.util.HashMap;

import test.bwie.com.mvp_demo.model.utils.HttpUtils;
import test.bwie.com.mvp_demo.model.utils.UriUtils;
import test.bwie.com.mvp_demo.view.iview.IHomeView;

/**
 * Created by ${仇海涛} on 2017/5/10.
 * 类的用途 ：
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    private HashMap<String, String> hashMap = new HashMap<>();

    public <T> void getDataFromServer(Class<T> cla) {

        HttpUtils.getDataFromM(UriUtils.uri1, hashMap, new HttpUtils.CallBackData<T>() {
            @Override
            public void callBackDataToM(T o) {
                getIview().callBackStr(o);
            }
        }, cla);

    }

}
