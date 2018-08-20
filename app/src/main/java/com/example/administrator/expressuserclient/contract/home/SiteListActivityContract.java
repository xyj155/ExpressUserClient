package com.example.administrator.expressuserclient.contract.home;

import android.content.Context;

import com.example.administrator.expressuserclient.commonUtil.DataLoader;
import com.example.administrator.expressuserclient.gson.POIGson;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public interface SiteListActivityContract {
    interface Model {
        void loadSiteList(Context context, double latitude, double longitude,DataLoader dataLoader);
    }

    interface View {
        void loadSitelist(List<POIGson.PoisBean> poiGson);

        void showLoading(String str);

        void hideLoading();

    }

    interface Presenter {

        void loadList(Context context, double latitude, double longitude);
    }
}
