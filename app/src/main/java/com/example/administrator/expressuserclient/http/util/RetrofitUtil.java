package com.example.administrator.expressuserclient.http.util;


import com.example.administrator.expressuserclient.http.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/25.
 */

public class RetrofitUtil {
    public static final String BASE_URL = "http://122.152.231.185/";
    public static final String BASE_URL_JAVA = "http://122.152.231.185:8080/";
    public static final String KUAIDINIAO = "http://api.kdniao.cc/";
    private Retrofit retrofit;
    private static RetrofitUtil sInstance;
    private static RetrofitUtil javaInstance;
    public RetrofitUtil(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public static RetrofitUtil getInstanceInJava(String url) {
        synchronized (RetrofitUtil.class) {
            if (javaInstance == null) {
                javaInstance = new RetrofitUtil(url);
            }
        }
        return javaInstance;
    }
    public static RetrofitUtil getInstance(String url) {
        synchronized (RetrofitUtil.class) {
            if (sInstance == null) {
                sInstance = new RetrofitUtil(url);
            }
        }
        return sInstance;
    }

    public API getServerices() {
        return retrofit.create(API.class);
    }
}