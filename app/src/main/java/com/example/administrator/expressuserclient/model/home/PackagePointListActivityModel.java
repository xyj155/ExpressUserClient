package com.example.administrator.expressuserclient.model.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.PackagePointListActivityContract;
import com.example.administrator.expressuserclient.gson.PackageSiteList;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/4/004.
 */

public class PackagePointListActivityModel implements PackagePointListActivityContract.Model {
    @Override
    public Observable<BaseGson<PackageSiteList>> getPackageStation(String userid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getPackageStation(userid);
    }
}
