package com.example.administrator.expressuserclient.model.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.HomeFragmentContract;
import com.example.administrator.expressuserclient.gson.BannerGson;
import com.example.administrator.expressuserclient.gson.NewsGson;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class HomeFragmentModel implements HomeFragmentContract.Model {
    @Override
    public Observable<BaseGson<BannerGson>> setBanner() {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryBanner();
    }

    @Override
    public Observable<BaseGson<NewsGson>> setNews() {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().setNewsList();
    }
}
