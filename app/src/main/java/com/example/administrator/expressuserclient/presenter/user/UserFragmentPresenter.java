package com.example.administrator.expressuserclient.presenter.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.BitmapUtils;
import com.example.administrator.expressuserclient.commonUtil.SPUtil;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.user.UserFragmentContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.model.user.UserFragmentModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/7/31.
 */

public class UserFragmentPresenter implements UserFragmentContract.Presenter {
    private UserFragmentContract.Model model = new UserFragmentModel();
    private UserFragmentContract.View view;
    private Context context;

    public UserFragmentPresenter(UserFragmentContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    private static final String TAG = "UserFragmentPresenter";


    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }


    @Override
    public void addUserAvar(String id, String files) {
        Bitmap getimage = BitmapUtils.getimage(files);
        File file = BitmapUtils.Bitmap2File(getimage);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        final RequestBody body = toRequestBody(id);
        model.uploadAvatar(body, part)
                .enqueue(new Callback<BaseGson<UserGson>>() {
                    @Override
                    public void onResponse(Call<BaseGson<UserGson>> call, Response<BaseGson<UserGson>> response) {
                        if (response.isSuccessful()) {
                            Log.i(TAG, "onResponse: " + response);
                            if (response.isSuccessful()) {
                                Log.i(TAG, "onResponse: " + response.body());
                                if (response.body().isSuccess()) {
                                    ToastUtil.showToastSuccess("上传头像成功！");
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    if (response.body().getData().size() > 0) {
                                        map.put("userhead", response.body().getData().get(0).getHead());
                                        SPUtil.getInstance().saveSPData(map).save();
                                    } else {
                                        ToastUtil.showToastError("上传头像失败" + response);
                                    }
                                } else {
                                    ToastUtil.showToastError("上传头像失败" + response);
                                }
                            }
                        } else {
                            ToastUtil.showToastError("上传头像失败" + response);
                        }

                    }

                    @Override
                    public void onFailure(Call<BaseGson<UserGson>> call, Throwable t) {
                        ToastUtil.showToastError("上传头像失败" + t.getMessage());
                    }
                });
    }
}
