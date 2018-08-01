package com.example.administrator.expressuserclient.model;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.MessageFragmentContract;
import com.example.administrator.expressuserclient.gson.PushGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/1.
 */

public class MessageFragmentModel implements MessageFragmentContract.Model {
    @Override
    public Observable<BaseGson<PushGson>> getPushList() {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getPushList();
    }
}
