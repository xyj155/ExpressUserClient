package com.example.administrator.expressuserclient.model.user;

import com.example.administrator.expressuserclient.contract.user.UserFragmentContract;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/7/31.
 */

public class UserFragmentModel implements UserFragmentContract.Model {

    @Override
    public Call<ResponseBody> uploadAvatar(RequestBody id, MultipartBody.Part file) {
        return RetrofitUtil
                .getInstance(RetrofitUtil.BASE_URL)
                .getServerices()
                .uploadAvatar(id, file);
    }
}
