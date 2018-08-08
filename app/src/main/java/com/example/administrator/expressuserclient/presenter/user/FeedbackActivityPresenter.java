package com.example.administrator.expressuserclient.presenter.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.user.FeedbackActivityContract;
import com.example.administrator.expressuserclient.gson.EmptyGson;
import com.example.administrator.expressuserclient.model.user.FeedbackActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/8/008.
 */

public class FeedbackActivityPresenter implements FeedbackActivityContract.Presenter {
    private FeedbackActivityContract.Model model = new FeedbackActivityModel();
    private FeedbackActivityContract.View view;

    public FeedbackActivityPresenter(FeedbackActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void createNewFeed(String im, String username, String userid, String content) {
        view.showDialog("提交中....");
        model.createNewFeed(im, username, userid, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastError(error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        view.hideDialog();
                        view.isSuccess(emptyGsonBaseGson.isSuccess());

                    }
                });
    }
}
