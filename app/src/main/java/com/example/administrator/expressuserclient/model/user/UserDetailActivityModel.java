package com.example.administrator.expressuserclient.model.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.user.UserDetailActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/6.
 */

public class UserDetailActivityModel implements UserDetailActivityContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> queryUserInfor(String id) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryUserInfor(id);
    }
}
