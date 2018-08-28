package com.example.administrator.expressuserclient.model.login;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.login.RegisterFragmentContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/28/028.
 */

public class RegisterFragmentModel implements RegisterFragmentContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> register(String username, String password, String tel) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().register(username,password,tel);
    }

    @Override
    public Observable<BaseGson<UserGson>> querySameUser(String username) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().querySameUser(username);
    }
}
