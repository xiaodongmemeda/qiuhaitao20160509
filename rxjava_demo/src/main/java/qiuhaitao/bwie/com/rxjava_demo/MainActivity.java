package qiuhaitao.bwie.com.rxjava_demo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d("memeda", "     " + Thread.currentThread().getName());
                Log.d("memeda", "subscribe:     " + "emitter   A");
                e.onNext("么么哒 A！");

            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("memeda", "     " + Thread.currentThread().getName());
                Log.d("memeda", "subscribe:     " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(observer);
        /*observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);*/


      /*  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("memeda", "emitter:      "+"a");
                e.onNext("我爱你a");
                Log.i("memeda", "emitter:      "+"b");
                e.onNext("我爱你b");
                Log.i("memeda", "emitter:      "+"c");
                e.onNext("我爱你c");
                Log.i("memeda", "emitter:      "+"a   onComplete");
                e.onComplete();
                Log.i("memeda", "emitter:      "+"d");
                e.onNext("我爱你d");
                Log.d("memeda","     "+ Thread.currentThread().getName());
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("memeda", "onNext:      "+s);
                Log.d("memeda","     "+ Thread.currentThread().getName());
            }
        });*/


        /*//RxJava 链式操作
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("么么哒a");
                e.onNext("么么哒b");
                e.onNext("么么哒c");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("memeda", "onNext:      "+"onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.i("memeda", "onNext:      "+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("memeda", "onNext:      "+"onError");
            }

            @Override
            public void onComplete() {
                Log.i("memeda", "onNext:      "+"onComplete");
            }
        });*/



        //创建一个上游 Observable：
        /*Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("memeda", "emitter:      "+"a");
                e.onNext("么么哒a");
                Log.i("memeda", "emitter:      "+"b");
                e.onNext("么么哒b");
                Log.i("memeda", "emitter:      "+"c");
                e.onNext("么么哒c");
                Log.i("memeda", "emitter:      "+"a   onComplete");
                e.onComplete();
                Log.i("memeda", "emitter:      "+"d");
                e.onNext("么么哒");
            }
        });

        //创建一个下游 Observer
        Observer<String> observer = new Observer<String>() {

            private Disposable disposable;
            private int i;
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("memeda", "onNext:      "+"onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(String value) {
                Log.i("memeda", "onNext:      "+value);
                i++;
                if (i==2){
                    Log.i("memeda", "           dispose");
                    disposable.dispose();
                    Log.i("memeda", "          "+disposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("memeda", "onNext:      "+"onError");
            }

            @Override
            public void onComplete() {
                Log.i("memeda", "onNext:      "+"onComplete");
            }
        };

        //建立连接
        observable.subscribe(observer);*/

    }
    private static Retrofit create(String uri) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return new Retrofit.Builder().baseUrl(uri)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
