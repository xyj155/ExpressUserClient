package com.example.administrator.expressuserclient.contract.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/6.
 */

public interface UserDetailActivityContract {
    interface Model {
        Observable<BaseGson<UserGson>> queryUserInfor(String id);
    }

    interface View {
        void showLoading(String msg);

        void hideLoading();

        void getUserInfor(BaseGson<UserGson> userGsonBaseGson);
    }

    interface Presenter {
        void queryUserInfor(String id);
    }
}
