package com.example.administrator.expressuserclient.presenter.login;

import android.app.Activity;
import android.content.Intent;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.SPUtil;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.login.RegisterFragmentContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.model.login.RegisterFragmentModel;
import com.example.administrator.expressuserclient.view.activity.SplashActivity;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/28/028.
 */

public class RegisterFragmentPresenter implements RegisterFragmentContract.Presenter {
    private RegisterFragmentContract.View view;
    private RegisterFragmentModel model = new RegisterFragmentModel();

    public RegisterFragmentPresenter(RegisterFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void register(final Activity context, String username, String password, String tel) {
        view.showDialog("注册中...");
        model.register(username, password, tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        System.out.println(error + "onError");
                        view.hideDialog();
                        ToastUtil.showToastWarning("注册失败" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> emptyGsonBaseGson) {
                        view.hideDialog();
                        if (emptyGsonBaseGson.isSuccess()) {
                            view.register(emptyGsonBaseGson);
                            ToastUtil.showToastSuccess("注册成功");
                            context.startActivity(new Intent(context, SplashActivity.class));
                            context.finish();
                            Map<String, Object> map = new HashMap<>();
                            map.put("username", emptyGsonBaseGson.getData().get(0).getUsername());
                            map.put("login", true);
                            map.put("userhead", emptyGsonBaseGson.getData().get(0).getHead() == null ? "" : emptyGsonBaseGson.getData().get(0).getHead());
                            map.put("id", emptyGsonBaseGson.getData().get(0).getId());
                            map.put("tel", emptyGsonBaseGson.getData().get(0).getUsertel() == null ? "" : map.put("tel", emptyGsonBaseGson.getData().get(0).getUsertel()));
                            SPUtil.getInstance().saveSPData(map).save();
                        } else {
                            ToastUtil.showToastWarning(emptyGsonBaseGson.getMsg());
                        }
                    }
                });
    }

    @Override
    public void querySameUser(String username) {
        model.querySameUser(username)
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
                        view.querySameUser(userGsonBaseGson);
                    }
                });
    }
}
