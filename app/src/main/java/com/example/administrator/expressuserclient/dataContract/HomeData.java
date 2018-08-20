package com.example.administrator.expressuserclient.dataContract;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.BannerGson;
import com.example.administrator.expressuserclient.gson.NewsGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class HomeData {
    private List<BannerGson> mBannerBean;

    private List<NewsGson> mOptimizationBean;

    public HomeData(BaseGson<BannerGson> bannerData, BaseGson<NewsGson> listBaseGson) {
        mBannerBean = bannerData.getData();
        mOptimizationBean = listBaseGson.getData();
    }

    public List<BannerGson> getBannerBean() {
        if (mBannerBean == null) {
            return new ArrayList<>();
        }
        return mBannerBean;
    }

    public List<NewsGson> getOptimizationBean() {
        if (mOptimizationBean == null) {
            return new ArrayList<>();
        }
        return mOptimizationBean;
    }
}
