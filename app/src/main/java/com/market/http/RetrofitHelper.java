package com.market.http;

import com.market.CosmeticMarketApp;
import com.market.utils.CommonUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
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
                            .writeTimeout(20, TimeUnit.SECONDS)                     //20秒写入超时
                            .readTimeout(20, TimeUnit.SECONDS)                      //同上
                            .build();
                }
            }
        }
    }

    /**
     * 为okhttp添加缓存，这里是考虑到服务器不支持缓存时，从而让okhttp支持缓存
     */
    private static class CacheInterceptor implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {

            // 有网络时 设置缓存超时时间1个小时
            int maxAge = 60 * 60;
            // 无网络时，设置超时为1天
            int maxStale = 60 * 60 * 24;
            Request request = chain.request();
            if (CommonUtil.isNetworkAvailable(CosmeticMarketApp.newInstance()))
            {
                //有网络时只从网络获取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            } else
            {
                //无网络时只从缓存中读取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.isNetworkAvailable(CosmeticMarketApp.newInstance()))
            {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else
            {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
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
        //龙哥吊的一比啊
    }

}
