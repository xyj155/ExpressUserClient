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
        Observable<BaseGson<UserGson>> register(String  username, String password, String tel);
    }

    interface View {
        void register(BaseGson<UserGson> baseGson);
    }

    interface Presenter {
        void register(Activity activity,String username, String password, String tel);
    }
}
