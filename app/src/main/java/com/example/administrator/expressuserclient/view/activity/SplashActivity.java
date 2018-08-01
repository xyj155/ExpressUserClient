package com.example.administrator.expressuserclient.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.example.administrator.expressuserclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;

import static com.tencent.bugly.crashreport.inner.InnerApi.context;

/**
 * Created by Administrator on 2018/8/1/001.
 */

public class SplashActivity extends AppCompatActivity {
    @InjectView(R.id.logo)
    ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
//        RemoteViews customView = new RemoteViews(context.getPackageName(), R.layout.jpush_notification_layout);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
//        mBuilder.setContent(customView)
//                .setContentIntent(new PendingIntent().getIntentSender())
//                .setWhen(System.currentTimeMillis())
//                .setTicker("")
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                .setOngoing(false)
//                .setSmallIcon(R.mipmap.ic_launcher);
//        Notification notify = mBuilder.build();
//        notify.contentView = customView;
//        notify.flags |= Notification.FLAG_AUTO_CANCEL; // 点击通知后通知栏消失
        // 通知id需要唯一，要不然会覆盖前一条通知
//        int notifyId = (int) System.currentTimeMillis();
//        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(notifyId, notify);


    }

}
