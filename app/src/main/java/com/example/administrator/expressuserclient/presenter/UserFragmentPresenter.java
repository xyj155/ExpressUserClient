package com.example.administrator.expressuserclient.presenter;

import android.content.Context;

import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.UserFragmentContract;
import com.example.administrator.expressuserclient.model.UserFragmentModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    public void addData(String id, String files) {
        File file = new File(files);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        RequestBody body = toRequestBody(id);
        model.uploadAvatar(body, part)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            ToastUtil.showToastSuccess("上传头像成功！");
                        }else {
                            ToastUtil.showToastError("上传头像失败" + response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ToastUtil.showToastError("上传头像失败" + t.getMessage());
                    }
                });
    }
}
