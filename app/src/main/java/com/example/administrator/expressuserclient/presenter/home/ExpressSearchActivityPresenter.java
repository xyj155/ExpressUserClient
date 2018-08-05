package com.example.administrator.expressuserclient.presenter.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.contract.home.ExpressSearchActivityContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.model.home.ExpressSearchActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public class ExpressSearchActivityPresenter implements ExpressSearchActivityContract.Presenter {
    private ExpressSearchActivityContract.Model model = new ExpressSearchActivityModel();
    private ExpressSearchActivityContract.View view;

    public ExpressSearchActivityPresenter(ExpressSearchActivityContract.View view) {
        this.view = view;
    }



    @Override
    public void expressSearch(String input) {
        model.expressSearch(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<OrderGson>>() {
                    @Override
                    public void onError(String error) {
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<OrderGson> packageSiteListBaseGson) {
                        view.expressSearch(packageSiteListBaseGson);
                    }
                });
    }
}
