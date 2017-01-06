package com.market.http;

import com.market.bean.BannerResp;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chenlong on 2017/1/5.
 */

public interface ApiService
{
    /**
     * 默认的api主接口
     */
    String BASE_URL = "http://10.0.2.2:8080/market/";

    @GET("home/hometopic.json")
    Observable<BannerResp> getBannerApi();

    //post请求
    /*@POST("User/login")
    Observable<BaseBean> login(@Field("mobile") String mobile
            , @Field("ccid") String ccid
            , @Field("password") String password);*/
}
