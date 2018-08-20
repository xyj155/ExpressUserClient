package com.example.administrator.expressuserclient.contract.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.DeliverHistory;

import java.util.List;

import rx.Observable;

/**
 * @author Administrator
 * @date 2018/8/18/018
 */

public interface DeliverHistoryActivityContract {
    interface Model {
        Observable<BaseGson<DeliverHistory>> getDeliverHistoryByUid(int uid);

    }

    interface View {
        void getDeliverHistoryByUid(List<DeliverHistory> deliverHistory);
    }

    interface Presenter {
        void getDeliverHistoryByUid(int uid);
    }
}
