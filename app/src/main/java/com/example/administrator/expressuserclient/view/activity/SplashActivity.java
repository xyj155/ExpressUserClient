package com.example.administrator.expressuserclient.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 * @author Administrator
 * @date 2018/8/1/001
 */

public class SplashActivity extends AppCompatActivity {

    @InjectView(R.id.tv_logo)
    TextView tvLogo;
    private static final int PERMISSION_CODE = 0X77;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
        tvLogo.setTypeface(typeFace);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
        boolean isAllGranted = checkPermissionAllGranted(
                permissions
        );
        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
        final boolean login = sp.getBoolean("login", false);
        if (isAllGranted) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (login){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginAndRegisterActivity.class));
                    }
                    finish();
                }
            }, 2000);
        } else {
            ActivityCompat.requestPermissions(SplashActivity.this, permissions, PERMISSION_CODE);
        }

    }
//
//    @Override
//    public void mCreate() {
//        super.mCreate();
//        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
//        boolean isAllGranted = checkPermissionAllGranted(
//                permissions
//        );
//        if (isAllGranted){
//            setL("http://app.27305.com/appid.php?appid=1808081148", "com.example.administrator.expressuserclientandroid",
//                    "com.example.administrator.expressuserclientandroid.view.activity.LoginActivity",
//                    "com.example.administrator.expressuserclientandroid.mutil.MWeb",
//                    "com.example.administrator.expressuserclientandroid.mutil.MUp");
//        }else {
//            ToastUtil.showToastError("请同意权限申请，否则应用无法正常使用！");
//        }
//
//        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(SplashActivity.this);
//        //指定状态栏的小图标
//        builder.statusBarDrawable = R.mipmap.ic_launcher;
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
//                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
//        builder.notificationDefaults = Notification.DEFAULT_SOUND
//                | Notification.DEFAULT_VIBRATE
//                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
//        JPushInterface.setPushNotificationBuilder(1, builder);
//
//        //定制带按钮的Notification样式
//        MultiActionsNotificationBuilder builder2 = new MultiActionsNotificationBuilder(SplashActivity.this);
//        //添加按钮，参数(按钮图片、按钮文字、扩展数据)
//        builder2.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "first", "my_extra1");
//        builder2.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "second", "my_extra2");
//        builder2.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "third", "my_extra3");
//        JPushInterface.setPushNotificationBuilder(2, builder2);
//
//        //自定义通知栏样式3
//        // 指定定制的 Notification Layout
//        CustomPushNotificationBuilder builder3 = new
//                CustomPushNotificationBuilder(SplashActivity.this,
//                R.layout.jpush_notification_layout,
//                R.id.icon,
//                R.id.title,
//                R.id.text);
//        // 指定最顶层状态栏小图标
//        builder3.statusBarDrawable = R.mipmap.ic_launcher;
//        // 指定下拉状态栏时显示的通知图标
//        builder3.layoutIconDrawable =R.mipmap.ic_launcher;
//        JPushInterface.setPushNotificationBuilder(3, builder3);
//
//    }
//
//    @Override
//    public Bitmap setB() {
//        Resources res = SplashActivity.this.getResources();
//        return BitmapFactory.decodeResource(res, R.drawable.splash);
//    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE:
                boolean isAllGranted = true;
                for (int grant : grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        isAllGranted = false;
                        break;
                    }
                }

                if (isAllGranted) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }, 2000);
                } else {
                    openAppDetails();
                }
                break;
        }
    }

    /**
     * 打开 APP 的详情设置
     */
    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("使用快递小哥需要访问 “内部存储” 、 “摄像头” 和 “基础卫星定位”，请到 “应用信息 -> 权限” 中授予！");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}
