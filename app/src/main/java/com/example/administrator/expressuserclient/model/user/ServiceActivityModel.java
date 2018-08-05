package com.example.administrator.expressuserclient.model.user;

import com.example.administrator.expressuserclient.config.APIConfig;
import com.example.administrator.expressuserclient.contract.user.ServiceActivityContract;
import com.example.administrator.expressuserclient.gson.TurLingGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import retrofit2.Call;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ServiceActivityModel implements ServiceActivityContract.Model {
    @Override
    public Call<TurLingGson> chatWithRobot(String key, String content) {
        return RetrofitUtil.getInstance(APIConfig.TURLING_URL).getServerices().chatWithTurling(key,content);
    }
}
