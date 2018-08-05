package com.example.administrator.expressuserclient.contract.order;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.OrderGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/3.
 */

public interface TicketFragmentContract {
    interface Model {
        Observable<BaseGson<OrderGson>> getOderList(String userid);
    }

    interface View {
        void showData(BaseGson<OrderGson> baseGson);
    }

    interface Presenter {
        void getOrderList(String userid);
    }
}
