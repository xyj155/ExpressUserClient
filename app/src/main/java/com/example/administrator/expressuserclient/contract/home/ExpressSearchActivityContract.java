package com.example.administrator.expressuserclient.contract.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.OrderGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public interface ExpressSearchActivityContract {
    interface Model {
        Observable<BaseGson<OrderGson>> expressSearch(String input);
    }

    interface View {
        void expressSearch(BaseGson<OrderGson> packageSiteListBaseGson);
    }

    interface Presenter {
        void expressSearch(String input);
    }
}
