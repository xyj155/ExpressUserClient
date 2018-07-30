package com.example.administrator.expressuserclient;

import android.app.Application;

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
    }
}
