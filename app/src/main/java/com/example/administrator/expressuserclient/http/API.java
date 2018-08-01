package com.example.administrator.expressuserclient.http;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.ExpressGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/28.
 */

public interface API {
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/User/login")
    Observable<BaseGson<UserGson>> loginWithUserName(@Field("username") String username,
                                                     @Field("password") String password);

    @Headers({
            "accept:*/*",
            "connection:Keep-Alive",
            "user-agent:Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)",
            "Content-Type:application/x-www-form-urlencoded"})
    @POST("/Ebusiness/EbusinessOrderHandle.aspx")
    Observable<ExpressGson> queryExpressGson(@Field("RequestData") String RequestData,
                                             @Field("EBusinessID") String EBusinessID,
                                             @Field("RequestType") String RequestType,
                                             @Field("DataSign") String DataSign,
                                             @Field("DataType") String DataType);

    @Streaming
    @Multipart
    @POST("/CurrierBrother/public/index.php/Index/User/loadHead")
    Call<ResponseBody> uploadAvatar(@Part MultipartBody.Part part);
}
