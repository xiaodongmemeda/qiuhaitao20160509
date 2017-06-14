package qiuhaitao.bwie.com.loginregist_demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ASUS on 2017/6/9.
 */

/**
 * LOGIN = http://169.254.23.9/mobile/index.php?act=login  //登录 POST
 * REG = http://169.254.23.9/mobile/index.php?act=login&op=register  //注册 POST
 */
public class HttpUtils {
    OkHttpClient client = new OkHttpClient();

    static public void login(String username, String password, String client, Observer<LoginBean> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://169.254.220.2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<LoginBean> observable = apiService.getLoginCode("login", username, password, client);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    static public void register(String username, String password,String password2,String email, String client, Observer<RegisterBean> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://169.254.220.2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<RegisterBean> observable = apiService.getRegCode("login","register", username, password, password2,email,client);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
