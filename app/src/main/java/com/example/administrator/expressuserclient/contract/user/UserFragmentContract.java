package com.example.administrator.expressuserclient.contract.user;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.UserGson;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/7/31.
 */

public interface UserFragmentContract {
    interface Model {
        Call<BaseGson<UserGson>> uploadAvatar(RequestBody id, MultipartBody.Part file);

    }

    interface View {
    }

    interface Presenter {
        void addData(String id, String files);
    }
}
