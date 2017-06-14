package test.bwie.com.mvp_demo.view.iview;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：
 */

public interface IHomeView<T> extends IView{
    void callBackStr(T s);
    void callBackError(String errorMsg,int error_code);
}
