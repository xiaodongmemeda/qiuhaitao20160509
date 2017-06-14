package test.bwie.com.mvp_demo.model.home;

import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 2017/6/6.
 */

public class HttpUtils {

     static class LoggingIntercepter implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Headers headers = request.headers();
            for(int x=0; x<headers.size() ; x++){
                String name = headers.name(x);
                String value = headers.value(x);
                Log.d("namevalue", "intercept: "+name+"========="+value);
            }
            okhttp3.Response proceed = chain.proceed(request);
            return proceed;
        }
    }


    public static void getDataRetrofitTest(String uri, final CallBackP<HomeBean> callBackP){
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new LoggingIntercepter()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomeService homeService = retrofit.create(HomeService.class);
        Call<HomeBean> call = homeService.listRepos();
        call.enqueue(new retrofit2.Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                HomeBean homeBean = response.body();
                Log.d("homebean",""+homeBean.getData().get(1).getNews_title());
                callBackP.callBack(homeBean);
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }

    public static<T> void getData(String uri, HashMap<String,String> hashMap, final Class<T> cla, final CallBackP<T> callBackP){
        RequestParams params = new RequestParams();
        params.setUri(uri);
        if (hashMap!=null){
            Iterator<String> iterator = hashMap.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                String value = hashMap.get(key);
            }
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                T t = gson.fromJson(result, cla);
                callBackP.callBack(t);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
     public interface CallBackP<T>{
        void  callBack(T t);
    }

}
