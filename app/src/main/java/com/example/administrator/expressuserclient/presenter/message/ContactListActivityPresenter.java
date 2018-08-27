package com.example.administrator.expressuserclient.presenter.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.message.ContactListActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.model.message.ContactListActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/26/026.
 */

public class ContactListActivityPresenter implements ContactListActivityContract.Presenter {
    private ContactListActivityContract.View view;
    private ContactListActivityModel model = new ContactListActivityModel();

    public ContactListActivityPresenter(ContactListActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void getContactList(String id) {
        model.getContactList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("获取联系人列表失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.loadContactList(userGsonBaseGson);
                    }
                });
    }
}
