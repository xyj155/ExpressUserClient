package com.example.administrator.expressuserclient.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.expressuserclient.commonUtil.DataLoader;
import com.example.administrator.expressuserclient.contract.SiteListActivityContract;
import com.example.administrator.expressuserclient.gson.POIGson;
import com.example.administrator.expressuserclient.model.SiteListActivityModel;
import com.google.gson.Gson;


/**
 * Created by Administrator on 2018/7/30/030.
 */

public class SiteListActivityPresenter implements SiteListActivityContract.Presenter {
    private SiteListActivityModel model = new SiteListActivityModel();
    private SiteListActivityContract.View view;


    public SiteListActivityPresenter(SiteListActivityContract.View view) {
        this.view = view;
    }


    @Override
    public void loadList(final Context context, final double latitude, final double longitude) {
        view.showLoading("数据加载中");
        Log.i(TAG, "run: " + latitude + "run" + longitude);
        model.loadSiteList(context, latitude, longitude, new DataLoader() {
            @Override
            public void getData(String data) {
                view.hideLoading();
                String replace = data.replace("[]", "null");
                Gson gson = new Gson();
                POIGson poiGson = gson.fromJson(replace, POIGson.class);
                if(poiGson.getPois().size()>0){
                    view.loadSitelist(poiGson.getPois());
                }
            }
        });
    }

    private static final String TAG = "SiteListActivityPresent";
}
