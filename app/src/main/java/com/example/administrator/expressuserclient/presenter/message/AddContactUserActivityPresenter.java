package com.example.administrator.expressuserclient.presenter.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.message.AddContactUserActivityContract;
import com.example.administrator.expressuserclient.gson.EmptyGson;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.model.message.AddContactUserActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/27/027.
 */

public class AddContactUserActivityPresenter implements AddContactUserActivityContract.Presenter {
    private AddContactUserActivityContract.View view;
    private AddContactUserActivityModel model = new AddContactUserActivityModel();

    public AddContactUserActivityPresenter(AddContactUserActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void queryContactByUid(String uid) {
        model.queryContactByUid(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        if (userGsonBaseGson.isSuccess()) {
                            view.loadUserList(userGsonBaseGson.getData());
                        }

                    }
                });
    }

    @Override
    public void setNewContact(String pid, String uid) {
        model.setNewContact(pid, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("添加失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        if (emptyGsonBaseGson.isSuccess()) {
                            ToastUtil.showToastSuccess("添加成功");
                        } else {
                            ToastUtil.showToastError("添加失败");
                        }
                    }
                });
    }
}
