package com.example.administrator.expressuserclient;

import android.app.Application;

import com.mob.MobSDK;
import com.tencent.bugly.Bugly;

import cn.jpush.im.android.api.JMessageClient;


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
        MobSDK.init(this);
        JMessageClient.init(this);
        JMessageClient.setDebugMode(true);    }

}
