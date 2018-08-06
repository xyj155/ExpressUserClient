package com.example.administrator.expressuserclient.presenter.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.user.UserDetailActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.model.user.UserDetailActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/6.
 */

public class UserDetailActivityPresenter implements UserDetailActivityContract.Presenter {
    private UserDetailActivityContract.Model model = new UserDetailActivityModel();
    private UserDetailActivityContract.View view;

    public UserDetailActivityPresenter(UserDetailActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void queryUserInfor(String id) {
        view.showLoading("数据获取中...");
        model.queryUserInfor(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("数据获取失败" + error);
                        view.hideLoading();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideLoading();
                        view.getUserInfor(userGsonBaseGson);
                    }
                });
    }
}
