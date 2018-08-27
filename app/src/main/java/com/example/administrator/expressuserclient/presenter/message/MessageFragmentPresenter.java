package com.example.administrator.expressuserclient.presenter.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.message.MessageFragmentContract;
import com.example.administrator.expressuserclient.gson.PushGson;
import com.example.administrator.expressuserclient.model.message.MessageFragmentModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/1.
 */

public class MessageFragmentPresenter implements MessageFragmentContract.Presenter {
    private MessageFragmentContract.Model model = new MessageFragmentModel();
    private MessageFragmentContract.View view;

    public MessageFragmentPresenter(MessageFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void getPushList() {
        view.showDialog("数据加载中...");
        model.getPushList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseGson<PushGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideDialog();
                        ToastUtil.showToastError("获取消息列表出错！");
                    }

                    @Override
                    public void onNext(BaseGson<PushGson> pushGsonBaseGson) {
                        if (pushGsonBaseGson.isSuccess()) {
                            view.setPushList(pushGsonBaseGson);
                            view.hideDialog();
                        } else {
                            ToastUtil.showToastError(pushGsonBaseGson.getMsg());
                            view.hideDialog();
                        }

                    }
                });
    }


}
