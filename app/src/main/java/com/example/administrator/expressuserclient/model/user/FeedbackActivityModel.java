package com.example.administrator.expressuserclient.model.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.user.FeedbackActivityContract;
import com.example.administrator.expressuserclient.gson.EmptyGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/8/008.
 */

public class FeedbackActivityModel implements FeedbackActivityContract.Model {


    @Override
    public Observable<BaseGson<EmptyGson>> createNewFeed(String im, String username, String userid, String content) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().UserFeedBack(im, username, userid, content);
    }
}
