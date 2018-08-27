package com.example.administrator.expressuserclient.contract.message;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.PushGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/1.
 */

public interface MessageFragmentContract {
    interface Model {
        Observable<BaseGson<PushGson>> getPushList();
    }

    interface View {
        void showDialog(String msg);

        void hideDialog();

        void setPushList(BaseGson<PushGson> pushList);
    }

    interface Presenter {
        void getPushList();
    }
}
