package test.bwei.com.eventbus_demo.presenter;

import test.bwei.com.eventbus_demo.view.iview.IView;

/**
 * Created by ${仇海涛} on 2017/5/23.
 * 类的用途 ：
 */

public class MPresenter<T extends IView> {
    private T t;
    public void attachView(T it){
        this.t = it;
    }
    public T getView(){
        return t;
    }

}
