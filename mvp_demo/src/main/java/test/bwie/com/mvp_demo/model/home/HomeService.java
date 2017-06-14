package test.bwie.com.mvp_demo.model.home;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ASUS on 2017/6/6.
 */

public interface HomeService {
    @GET("type/1/p/11")
    Call<HomeBean> listRepos();
}
