package com.example.administrator.expressuserclient.model.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.DeliverHistoryActivityContract;
import com.example.administrator.expressuserclient.gson.DeliverHistory;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/18/018.
 */

public class DeliverHistoryActivityModel implements DeliverHistoryActivityContract.Model {
    @Override
    public Observable<BaseGson<DeliverHistory>> getDeliverHistoryByUid(int uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getDeliverHistoryByUid(uid);
    }
}
