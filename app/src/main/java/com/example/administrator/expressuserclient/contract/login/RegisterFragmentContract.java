package com.example.administrator.expressuserclient.contract.login;

import android.app.Activity;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/28/028.
 */

public interface RegisterFragmentContract {
    interface Model {
        Observable<BaseGson<UserGson>> register(String username, String password, String tel);

        Observable<BaseGson<UserGson>> querySameUser(String username);

    }

    interface View {
        void register(BaseGson<UserGson> baseGson);

        void querySameUser(BaseGson<UserGson> baseGson);

        void showDialog(String msg);

        void hideDialog();
    }

    interface Presenter {
        void register(Activity activity, String username, String password, String tel);
        void querySameUser(String username);
    }
}
