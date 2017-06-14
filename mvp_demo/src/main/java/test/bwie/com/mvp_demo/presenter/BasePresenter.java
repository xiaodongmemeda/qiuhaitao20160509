package test.bwie.com.mvp_demo.presenter;

import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;
import test.bwie.com.mvp_demo.R;
import test.bwie.com.mvp_demo.view.iview.IView;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：
 */

public class BasePresenter<T extends IView> {
    private T t;
    ImageOptions options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.ic_launcher).build();
    public void attachView(T t){
        this.t = t;
    }
    public T getView(){
        return t;
    }
    public void setImg(ImageView img, String uri){
        x.image().bind(img,uri,options);
    }
}
