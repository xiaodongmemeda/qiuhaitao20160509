package test.bwie.com.qiuhaitao20160509.utils;

import com.google.gson.Gson;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：Gson 工具类
 */

public class GsonUtils {
    public  static <T>T getBean(String json,Class<T> cla){
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(json,cla);
        return  t;
    }
}
