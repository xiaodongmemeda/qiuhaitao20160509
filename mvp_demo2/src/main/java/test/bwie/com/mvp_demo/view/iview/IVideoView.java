package test.bwie.com.mvp_demo.view.iview;

/**
 * Created by ${仇海涛} on 2017/5/10.
 * 类的用途 ：
 */

public interface IVideoView<T> extends IView{
    void callBackStr(T string);
    void callBackerr(String string,int errCode);
}
