package com.example.administrator.expressuserclient;

import android.app.Application;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;

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
        CrashReport.initCrashReport(getApplicationContext(), "f2c794a75c", true);
        Beta.checkUpgrade();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
