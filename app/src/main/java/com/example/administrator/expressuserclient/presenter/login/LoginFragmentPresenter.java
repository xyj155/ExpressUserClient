package com.example.administrator.expressuserclient.presenter.login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.base.BaseObserver;
import com.example.administrator.expressuserclient.commonUtil.SPUtil;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.login.LoginFragmentContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.model.login.LoginActivityModel;
import com.example.administrator.expressuserclient.view.activity.SplashActivity;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @author Administrator
 * @date 2018/7/28
 */

public class LoginFragmentPresenter implements LoginFragmentContract.Presenter {
    private LoginFragmentContract.Model loginActivityModel = new LoginActivityModel();
    private LoginFragmentContract.View view;

    public LoginFragmentPresenter(LoginFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void loginWithUserName(final Activity context, final String username, String password) {
        if (username.isEmpty() && password.isEmpty()) {
            ToastUtil.showToastInfor("请输入账号密码！");
        } else {
            view.showDialog("登陆中....");
            loginActivityModel.loginWithUserName(context, username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                        @Override
                        public void onError(String error) {
                            view.hideDialog();
                            Log.i(TAG, "onError: "+error);
                            ToastUtil.showToastError("登陆失败，错误信息：" + error);
                        }

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                            view.hideDialog();
                            if (userGsonBaseGson.isSuccess()) {
                                ToastUtil.showToastSuccess("登陆成功");
                                context.startActivity(new Intent(context, SplashActivity.class));
                                context.finish();
                                Map<String, Object> map = new HashMap<>();
                                map.put("username", userGsonBaseGson.getData().get(0).getUsername());
                                map.put("login", true);
                                map.put("userhead", userGsonBaseGson.getData().get(0).getHead().isEmpty()?"":userGsonBaseGson.getData().get(0).getHead());
                                map.put("id", userGsonBaseGson.getData().get(0).getId());
                                if (userGsonBaseGson.getData().get(0).getUsertel()==null){
                                    map.put("tel", "");
                                }else {
                                    map.put("tel", userGsonBaseGson.getData().get(0).getUsertel());
                                }
                                SPUtil.getInstance().saveSPData(map).save();
                            } else {
                                ToastUtil.showToastInfor("登陆失败，错误：" + userGsonBaseGson.getMsg());
                            }
                        }

                    });
        }

    }

    private static final String TAG = "LoginFragmentPresenter";
}
