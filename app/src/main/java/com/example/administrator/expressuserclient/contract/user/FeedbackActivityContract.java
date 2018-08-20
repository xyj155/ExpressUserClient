package com.example.administrator.expressuserclient.contract.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.EmptyGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/8/008.
 */

public interface FeedbackActivityContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> createNewFeed(String im, String username, String userid, String content);
    }

    interface View {
        void showDialog(String msg);

        void hideDialog();

        void isSuccess(boolean success);

    }

    interface Presenter {
        void createNewFeed(String im, String username, String userid, String content);
    }
}
