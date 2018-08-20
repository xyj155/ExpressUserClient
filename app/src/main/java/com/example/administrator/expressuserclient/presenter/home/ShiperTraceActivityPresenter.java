package com.example.administrator.expressuserclient.presenter.home;

import android.support.annotation.MainThread;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.ShiperTraceActivityContract;
import com.example.administrator.expressuserclient.gson.ShiperTraceGson;
import com.example.administrator.expressuserclient.gson.ShiptraceBean;
import com.example.administrator.expressuserclient.model.home.ShiperTraceActivityModel;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class ShiperTraceActivityPresenter implements ShiperTraceActivityContract.Presenter {
    private ShiperTraceActivityContract.Model model = new ShiperTraceActivityModel();
    private ShiperTraceActivityContract.View view;

    public ShiperTraceActivityPresenter(ShiperTraceActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void loadFromShipe(String num) {
        view.showLoading("数据加载中！");
        model.queryPackageByNum(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new   BaseObserver<BaseGson<ShiperTraceGson<ShiptraceBean>>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoading();
                        ToastUtil.showToastError("查询出错"+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<ShiperTraceGson<ShiptraceBean>> baseGson) {
                        view.hideLoading();
                        if (baseGson.isSuccess()) {
                            view.loadFromShiper(baseGson);
                        }else{
                            ToastUtil.showToastInfor(baseGson.getMsg());
                        }
                    }
                });


    }
}
