package com.example.administrator.expressuserclient.contract.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.BannerGson;
import com.example.administrator.expressuserclient.gson.NewsGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public interface HomeFragmentContract {
    interface Model {

        Observable<BaseGson<BannerGson>> setBanner();

        Observable<BaseGson<NewsGson>> setNews();
    }

    interface View {

        void setBanner(List<BannerGson> bannerGsonBaseGson, List<NewsGson> baseGson);


    }

    interface Presenter {

        void setMainFragmentData();
    }
}
