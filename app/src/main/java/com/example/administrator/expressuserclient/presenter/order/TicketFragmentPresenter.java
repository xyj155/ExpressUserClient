package com.example.administrator.expressuserclient.presenter.order;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.order.TicketFragmentContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.model.order.TicketFragmentModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/3.
 */

public class TicketFragmentPresenter implements TicketFragmentContract.Presenter {
    private TicketFragmentContract.Model model = new TicketFragmentModel();
    private TicketFragmentContract.View view;

    public TicketFragmentPresenter(TicketFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void getOrderList(String userid) {
        model.getOderList(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<OrderGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("订单请求失败！错误" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<OrderGson> orderGsonBaseGson) {
                        if (orderGsonBaseGson.getData().size() > 0) {
                            view.showData(orderGsonBaseGson);
                            ToastUtil.showToastUsual("今天有订单哦！要辛苦你了");
                        } else {
                            view.showData(orderGsonBaseGson);
                            ToastUtil.showToastInfor("你今天没有要派送的订单哦！");
                        }

                    }
                });
    }
}
