package com.market.http;

import android.content.Context;

import com.market.CosmeticMarketApp;
import com.market.view.activity.base.LoginActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlong on 2017/1/5.
 */

public class RetrofitHelper
{
    private static OkHttpClient mOkHttpClient;

    static
    {
        initOkHttpClient();
    }

    private static void initOkHttpClient()
    {
        if (mOkHttpClient == null)
        {
            synchronized (RetrofitHelper.class)
            {
                if (mOkHttpClient == null)
                {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)                         //设置自动重连
                            .connectTimeout(5, TimeUnit.SECONDS)                    //5秒连接超时
                            .addInterceptor(new UserIdInterceptor())
                            .writeTimeout(20, TimeUnit.SECONDS)                     //20秒写入超时
                            .readTimeout(20, TimeUnit.SECONDS)                      //同上
                            .build();
                }
            }
        }
    }

    private static class UserIdInterceptor implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();

            String uid = CosmeticMarketApp.newInstance()
                    .getSharedPreferences(LoginActivity.TEXT, Context.MODE_PRIVATE)
                    .getString(LoginActivity.USERId_LOGIN, "");
            request = request.newBuilder()
                    .addHeader("userid", uid)
                    .build();

            return chain.proceed(request);
        }
    }

    /**
     * retrofit封装okHttp (这里的api较少 只写一个类  可以抽取成公共的泛型)
     * <p>
     * private static <T> T getApiService(Class<T> clazz, String baseUrl)
     * {
     * ...
     * return retrofit.create(clazz);
     * }
     *
     * @return 返回的是接口ApiService
     */
    public static ApiService getApiService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)        //联网地址
                .client(mOkHttpClient)    //okHttpClient
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) //gson格式化
                .build();
        return retrofit.create(ApiService.class);
    }

}
