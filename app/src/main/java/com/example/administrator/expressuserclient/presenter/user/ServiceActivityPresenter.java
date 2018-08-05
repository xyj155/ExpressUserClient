package com.example.administrator.expressuserclient.presenter.user;

import android.util.Log;

import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.config.APIConfig;
import com.example.administrator.expressuserclient.contract.user.ServiceActivityContract;
import com.example.administrator.expressuserclient.gson.TurLingGson;
import com.example.administrator.expressuserclient.model.user.ServiceActivityModel;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ServiceActivityPresenter implements ServiceActivityContract.Presenter {
    private ServiceActivityContract.Model model = new ServiceActivityModel();
    private ServiceActivityContract.View view;

    public ServiceActivityPresenter(ServiceActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void chatWithRobot(String content) throws UnsupportedEncodingException {
//        String input = URLEncoder.encode(content, "utf-8");
        model.chatWithRobot(APIConfig.APIKEY, content)
                .enqueue(new Callback<TurLingGson>() {
                    @Override
                    public void onResponse(Call<TurLingGson> call, Response<TurLingGson> response) {
                        view.hideLoading();
                        Log.i(TAG, "onNext: " + response.body().getCode());
                        if (response.isSuccessful()) {
                            if (response.body().getCode() == 100000) {
                                view.loadContent(response.body());
                            }else {
                                ToastUtil.showToastError("换一个词问快递小哥吧！");
                            }
                        } else {
                            ToastUtil.showToastError("请求出错！");
                        }
                    }

                    @Override
                    public void onFailure(Call<TurLingGson> call, Throwable t) {
                        System.out.println(t.getMessage());
                        ToastUtil.showToastError("服务器异常咯！" + t.getMessage());
                    }
                });
    }

    private static final String TAG = "ServiceActivityPresente";
}
