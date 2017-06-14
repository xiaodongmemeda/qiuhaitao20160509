package test.bwei.com.eventbus_demo.model;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${仇海涛} on 2017/5/23.
 * 类的用途 ：
 */

public class HttpUtils {
    private static CallBackData mcallBackData ;


   private static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String json = (String) msg.obj;
                    mcallBackData.callBack(json);
                    break;

                default:
                    break;
            }
        }
    };


    public static void getdata(String uri,CallBackData callBackData){
        mcallBackData = callBackData;

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(uri).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                String string = null;
                try {
                    string = response.body().string();
                    Message message = Message.obtain();
                    message.obj = string;
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*Gson gson = new Gson();
                T t = gson.fromJson(string, cla);
                callBackData.callBack(t);*/
            }
        });
    }
    public interface CallBackData<T>{
        void callBack(T t);
    }
}
