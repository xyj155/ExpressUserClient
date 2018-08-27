package com.example.administrator.expressuserclient.model.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.message.ContactListActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/26/026.
 */

public class ContactListActivityModel implements ContactListActivityContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> getContactList(String id) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryContactList(id);
    }
}
