package com.example.administrator.expressuserclient;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;

import static com.example.administrator.expressuserclient.weight.FancyAlertDialog.TAG;

/**
 * Created by Administrator on 2018/7/28.
 */

public class App extends Application {
    public static App app = null;

    public static App getInstance() {
        if (app == null) {
            return new App();
        } else {
            return app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Bugly.init(getApplicationContext(), "f2c794a75c", false);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

}
