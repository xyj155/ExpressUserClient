package com.example.administrator.expressuserclient.http.network;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by $wu on 2017-08-04 上午 10:29.
 * Okhttp的log日志拦截器
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        //请求前先打印日志
        Request request = chain.request();
        Response response = chain.proceed(chain.request());
        //   LogUtil.log("LogInterceptor", "response" + response.body().string());
        return response;
    }
}
