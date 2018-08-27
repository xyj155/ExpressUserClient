package com.example.administrator.expressuserclient.model.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.message.AddContactUserActivityContract;
import com.example.administrator.expressuserclient.gson.EmptyGson;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/27/027.
 */

public class AddContactUserActivityModel implements AddContactUserActivityContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> queryContactByUid(String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryContactByUid(uid);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> setNewContact(String pid, String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().setNewContact(pid,uid);
    }


}
