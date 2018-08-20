package com.example.administrator.expressuserclient.contract.user;

import com.example.administrator.expressuserclient.gson.TurLingGson;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;

/**
 * Created by Administrator on 2018/8/3.
 */

public interface ServiceActivityContract {
    interface Model {
        Call<TurLingGson> chatWithRobot(String key, String content);
    }

    interface View {
        void showLoading(String msg);

        void hideLoading();

        void loadContent(TurLingGson turLingGson);
    }

    interface Presenter {
        void chatWithRobot(String content) throws UnsupportedEncodingException;
    }
}
