package com.example.administrator.expressuserclient.model.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.ExpressSearchActivityContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public class ExpressSearchActivityModel implements ExpressSearchActivityContract.Model {
    @Override
    public Observable<BaseGson<OrderGson>> expressSearch(String input) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().searchOrder(input);
    }
}
