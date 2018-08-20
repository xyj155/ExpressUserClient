package com.example.administrator.expressuserclient.contract.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.ShiperTraceGson;
import com.example.administrator.expressuserclient.gson.ShiptraceBean;

import rx.Observable;

/**
 * @author Administrator
 * @date 2018/8/20/020
 */

public interface ShiperTraceActivityContract {
    interface Model {
        Observable<BaseGson<ShiperTraceGson<ShiptraceBean>>> queryPackageByNum(String num);
    }

    interface View {
        void loadFromShiper(BaseGson<ShiperTraceGson<ShiptraceBean>> baseGson);

        void showLoading(String msg);

        void hideLoading();
    }

    interface Presenter {
        void loadFromShipe(String num);
    }
}
