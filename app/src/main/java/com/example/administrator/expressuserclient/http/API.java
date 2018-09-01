package com.example.administrator.expressuserclient.http;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.BannerGson;
import com.example.administrator.expressuserclient.gson.DeliverHistory;
import com.example.administrator.expressuserclient.gson.EmptyGson;
import com.example.administrator.expressuserclient.gson.ExpressGson;
import com.example.administrator.expressuserclient.gson.NewsGson;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.gson.PushGson;
import com.example.administrator.expressuserclient.gson.ShiperTraceGson;
import com.example.administrator.expressuserclient.gson.ShiptraceBean;
import com.example.administrator.expressuserclient.gson.TurLingGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    //登陆
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Users/login")
    Observable<BaseGson<UserGson>> loginWithUserName(@Field("username") String username,
                                                     @Field("password") String password);

    //注册
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Users/register")
    Observable<BaseGson<UserGson>> register(@Field("username") String username,
                                                     @Field("password") String password,
                                                     @Field("tel") String tel);    //注册
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Users/querySameUser")
    Observable<BaseGson<UserGson>> querySameUser(@Field("username") String username);

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

    //上传头像
    @Streaming
    @Multipart
    @POST("/CurrierBrother/public/index.php/Index/Users/loadHead")
    Call<BaseGson<UserGson>> uploadAvatar(@Part("id") RequestBody id, @Part MultipartBody.Part part);

    //消息推送
    @GET("/CurrierBrother/public/index.php/Index/Push/newsPush")
    Observable<BaseGson<PushGson>> getPushList();

    //订单列表
    @FormUrlEncoded
    @POST("/currierbrother/solution/vrp")
    Observable<BaseGson<OrderGson>> getOrderList(@Field("userid") String userid);

    //配送列表
    @FormUrlEncoded
    @POST("/currierbrother/solution/vrp")
    Observable<BaseGson<OrderGson>> getPackageStation(@Field("userid") String userid);


    //图灵机器人
    @FormUrlEncoded
    @POST("http://www.tuling123.com/openapi/api")
    Call<TurLingGson> chatWithTurling(@Field("key") String key,
                                      @Field("info") String info);


    //查询订单
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Order/searchOrder")
    Observable<BaseGson<OrderGson>> searchOrder(@Field("input") String input);


    //查询用户信息
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/index/Users/queryUserInfor")
    Observable<BaseGson<UserGson>> queryUserInfor(@Field("id") String id);


    //提交意见反馈
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/index/Feedback/UserFeedBack")
    Observable<BaseGson<EmptyGson>> UserFeedBack(@Field("im") String im,
                                                 @Field("username") String username,
                                                 @Field("id") String id,
                                                 @Field("content") String content);

    //派件记录
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/index/Order/getDeliverHistoryByUid")
    Observable<BaseGson<DeliverHistory>> getDeliverHistoryByUid(@Field("uid") int id);

    //派件记录
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Package/searchPackage")
    Observable<BaseGson<ShiperTraceGson<ShiptraceBean>>> queryTraceByNum(@Field("num") String num);

    //查询轮播图
    @GET("/CurrierBrother/public/index.php/Index/Home/serBanner")
    Observable<BaseGson<BannerGson>> queryBanner();

    //查询主页消息
    @GET("/CurrierBrother/public/index.php/Index/Home/setNewsList")
    Observable<BaseGson<NewsGson>> setNewsList();

    //添加好友
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Contact/setUserContact")
    Observable<BaseGson<EmptyGson>> setNewContact(@Field("pid") String pid, @Field("uid") String uid);

    //查询好友
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Contact/queryContactList")
    Observable<BaseGson<UserGson>> queryContactList(@Field("uid") String uid);

    //z账号查询好友
    @FormUrlEncoded
    @POST("/CurrierBrother/public/index.php/Index/Contact/queryContactByUid")
    Observable<BaseGson<UserGson>> queryContactByUid(@Field("uid") String uid);
}
