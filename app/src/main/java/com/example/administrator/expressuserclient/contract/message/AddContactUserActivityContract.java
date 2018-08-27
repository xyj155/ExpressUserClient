package com.example.administrator.expressuserclient.contract.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.EmptyGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/27/027.
 */

public interface AddContactUserActivityContract {
    interface Model {
        Observable<BaseGson<UserGson>> queryContactByUid(String uid);

        Observable<BaseGson<EmptyGson>> setNewContact(String pid,String uid);
    }

    interface View {
        void loadUserList(List<UserGson> userGsonBaseGson);

    }

    interface Presenter {
        void queryContactByUid(String uid);

        void setNewContact(String pid, String uid);
    }
}
