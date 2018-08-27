package com.example.administrator.expressuserclient.contract.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/26/026.
 */

public interface ContactListActivityContract {
    interface Model {
        Observable<BaseGson<UserGson>> getContactList(String id);
    }

    interface View {
        void loadContactList(BaseGson<UserGson> userGsonBaseGson);
    }

    interface Presenter {
        void getContactList(String id);
    }
}
