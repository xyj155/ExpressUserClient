package com.example.administrator.expressuserclient.model.order;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.order.TicketFragmentContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/3.
 */

public class TicketFragmentModel implements TicketFragmentContract.Model {
    @Override
    public Observable<BaseGson<OrderGson>> getOderList(String userid) {
        return RetrofitUtil.
                getInstanceInJava(RetrofitUtil.BASE_URL_JAVA)
                .getServerices().getOrderList(userid);
    }
}
