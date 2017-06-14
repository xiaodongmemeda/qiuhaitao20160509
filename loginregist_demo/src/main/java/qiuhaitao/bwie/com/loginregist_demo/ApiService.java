package qiuhaitao.bwie.com.loginregist_demo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ASUS on 2017/6/9.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("mobile/index.php")
    Observable<RegisterBean> getRegCode(@Field("act") String sort,
                                  @Field("op") String op,
                                  @Field("username") String username,
                                  @Field("password") String password,
                                  @Field("password_confirm") String password_confirm,
                                  @Field("email") String email,
                                  @Field("client") String client);

    @FormUrlEncoded
    @POST("mobile/index.php")
    Observable<LoginBean> getLoginCode(@Field("act") String sort,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("client") String client);

}
