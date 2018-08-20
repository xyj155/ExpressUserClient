package com.example.administrator.expressuserclient.model.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.ShiperTraceActivityContract;
import com.example.administrator.expressuserclient.gson.ShiperTraceGson;
import com.example.administrator.expressuserclient.gson.ShiptraceBean;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class ShiperTraceActivityModel implements ShiperTraceActivityContract.Model {


    @Override
    public Observable<BaseGson<ShiperTraceGson<ShiptraceBean>>> queryPackageByNum(String num) {
          return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryTraceByNum(num);
    }
}
