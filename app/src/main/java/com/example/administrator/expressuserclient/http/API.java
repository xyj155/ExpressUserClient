package com.example.administrator.expressuserclient.http;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/28.
 */

public interface API {
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/User/login")
    Observable<BaseGson<UserGson>> loginWithUserName(@Field("username") String username,
                                         @Field("password") String password);

}
