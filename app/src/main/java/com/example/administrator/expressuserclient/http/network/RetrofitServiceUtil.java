package com.example.administrator.expressuserclient.http.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by $wu on 2017-08-09 上午 9:21.
 * 创建单例获取RetrofitService
 */

public class RetrofitServiceUtil {

    private static RetrofitServiceUtil instance;
    private static OkHttpClient okHttpClient;

    private RetrofitServiceUtil() {
        //设置okhttp的响应式时间
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())             //添加拦截器未全局设置header
                .addInterceptor(new LogInterceptor());               //添加拦截器打印日志


    }

    private static RetrofitServiceUtil getInstance() {
        if (instance == null) {
            instance = new RetrofitServiceUtil();
        }
        return instance;
    }

    public static RetrofitService getRetrofitService() {
        return getInstance().innGetRetrofitService();
    }

    private RetrofitService innGetRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.shaodianbao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(RetrofitService.class);
    }


}
