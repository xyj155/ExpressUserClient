package com.example.administrator.expressuserclient.presenter.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.PackagePointListActivityContract;
import com.example.administrator.expressuserclient.gson.PackageSiteList;
import com.example.administrator.expressuserclient.model.home.PackagePointListActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/4/004.
 */

public class PackagePointListActivityPresenter implements PackagePointListActivityContract.Presenter {
    private PackagePointListActivityContract.Model model = new PackagePointListActivityModel();
    private PackagePointListActivityContract.View view;

    public PackagePointListActivityPresenter(PackagePointListActivityContract.View view) {
        this.view = view;
}

    @Override
    public void setPackageStation(String userid) {
        view.showDialog("数据加载中...");
        model.getPackageStation(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PackageSiteList>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("服务器出错！" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PackageSiteList> packageSiteListBaseGson) {
                        view.hideDialog();
                        view.showData(packageSiteListBaseGson);
                    }
                });

    }
}
