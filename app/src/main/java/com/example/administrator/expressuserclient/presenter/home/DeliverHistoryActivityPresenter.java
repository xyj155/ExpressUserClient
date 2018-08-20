package com.example.administrator.expressuserclient.presenter.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.DeliverHistoryActivityContract;
import com.example.administrator.expressuserclient.gson.DeliverHistory;
import com.example.administrator.expressuserclient.model.home.DeliverHistoryActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @author Administrator
 * @date 2018/8/18/018
 */

public class DeliverHistoryActivityPresenter implements DeliverHistoryActivityContract.Presenter {
    private DeliverHistoryActivityContract.Model model = new DeliverHistoryActivityModel();
    private DeliverHistoryActivityContract.View view;

    public DeliverHistoryActivityPresenter(DeliverHistoryActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void getDeliverHistoryByUid(int uid) {
        model.getDeliverHistoryByUid(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<DeliverHistory>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("历史派件记录请求错误！"+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<DeliverHistory> deliverHistoryBaseGson) {
                        if (deliverHistoryBaseGson.isSuccess()) {
                            if (deliverHistoryBaseGson.getData().size() > 0) {
                                view.getDeliverHistoryByUid(deliverHistoryBaseGson.getData());
                            }
                        } else {
                            ToastUtil.showToastInfor("你还没有派件记录哦！");
                        }
                    }
                });
    }
}
