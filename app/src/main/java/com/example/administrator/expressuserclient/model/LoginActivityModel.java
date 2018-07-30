package com.example.administrator.expressuserclient.model;

import android.app.Activity;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.LoginActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/28.
 */

public class LoginActivityModel implements LoginActivityContract.Model {

    @Override
    public Observable<BaseGson<UserGson>> loginWithUserName(Activity context, String username, String password) {
        return RetrofitUtil.getInstance().getServerices().loginWithUserName(username, password);
    }
}
