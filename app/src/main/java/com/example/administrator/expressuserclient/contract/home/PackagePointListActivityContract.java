package com.example.administrator.expressuserclient.contract.home;

import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.gson.PackageSiteList;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/4/004.
 */

public interface PackagePointListActivityContract {
    interface Model {
        Observable<BaseGson<PackageSiteList>> getPackageStation( String userid);
    }

    interface View {
        void showDialog(String msg);

        void hideDialog();

        void showData(BaseGson<PackageSiteList> packageStation);
    }

    interface Presenter {

        void setPackageStation(String userid);
    }
}
