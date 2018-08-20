package com.example.administrator.expressuserclient.presenter.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.HomeFragmentContract;
import com.example.administrator.expressuserclient.dataContract.HomeData;
import com.example.administrator.expressuserclient.gson.BannerGson;
import com.example.administrator.expressuserclient.gson.NewsGson;
import com.example.administrator.expressuserclient.model.home.HomeFragmentModel;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter {
    private HomeFragmentModel model = new HomeFragmentModel();
    private HomeFragmentContract.View view;

    public HomeFragmentPresenter(HomeFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void setMainFragmentData() {
        Observable.zip(model.setBanner(), model.setNews(), new Func2<BaseGson<BannerGson>, BaseGson<NewsGson>, HomeData>() {
            @Override
            public HomeData call(BaseGson<BannerGson> bannerGsonBaseGson, BaseGson<NewsGson> baseGson) {
                return new HomeData(bannerGsonBaseGson, baseGson);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeData homeData) {
                        view.setBanner(homeData.getBannerBean(), homeData.getOptimizationBean());
                    }
                });

    }
}
