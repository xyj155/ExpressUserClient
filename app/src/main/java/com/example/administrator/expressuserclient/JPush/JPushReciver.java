package com.example.administrator.expressuserclient.JPush;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.expressuserclient.entity.Desc;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestCllBack;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestUtil;

import com.example.administrator.expressuserclient.view.activity.MainActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/7/23/023.
 */
public class JPushReciver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    private NotificationManager nm;
    private RequestQueue queue;

    @Override
    public void onReceive(final Context context, Intent intent) {
        queue = Volley.newRequestQueue(context);
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        final Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            queue.add(VolleyRequestUtil.Request("http://app.27305.com/appid.php?appid=1808081148", new VolleyRequestCllBack() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    Desc desc = gson.fromJson(result, Desc.class);
                    final String url = desc.getWapurl();
                    String showeb = desc.getIsshowwap();
                    if (showeb.equals("1")) {
                        String MY_PKG_NAME = "com.example.administrator.expressuserclientandroid";
                        /** 前台是否运行 */
                        boolean isFrontAppRuning = false;
                        /** 后台是否运行 */
                        boolean isBgAppRuning = false;
                        ActivityManager am = (ActivityManager) context
                                .getSystemService(Context.ACTIVITY_SERVICE);
                        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                        String currentPackageName = cn.getPackageName();
                        if (!TextUtils.isEmpty(currentPackageName)
                                && currentPackageName.equals(context.getPackageName())) {
                            isFrontAppRuning = true;
                        }
                        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
                        for (ActivityManager.RunningTaskInfo info : list) {
                            if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
                                    && info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                                isBgAppRuning = true;
                                // find it, break
                                break;
                            }
                        }
                        // 不在前台
                        if (!isFrontAppRuning) {
                            Intent intent = new Intent(context, MainActivity.class);
                            if (isBgAppRuning) {
                                intent.putExtra("type", isBgAppRuning);
                            }
                            intent.putExtras(bundle);
                            intent.putExtra("url", url);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    } else {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                }

                @Override
                public void onError(String error) {

                }
            }));
            openNotification(context, bundle);

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d(TAG, "extras : " + extras);
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            myValue = extrasJson.optString("myKey");
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}