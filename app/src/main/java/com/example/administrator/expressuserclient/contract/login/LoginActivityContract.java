package com.example.administrator.expressuserclient.contract.login;

import android.app.Activity;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/28.
 */

public interface LoginActivityContract {
    interface Model {
        Observable<BaseGson<UserGson>> loginWithUserName(Activity context,String username, String password);
    }

    interface View {

        void showDialog(String msg);

        void hideDialog();
    }

    interface Presenter {
        void loginWithUserName(Activity context, String username, String password);
    }
}
