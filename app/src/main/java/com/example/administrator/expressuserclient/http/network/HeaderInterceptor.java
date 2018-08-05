package com.example.administrator.expressuserclient.http.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by $wu on 2017-08-04 上午 10:28.
 * okhttp的拦截器添加header
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("versionnumber", "2.1")
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
