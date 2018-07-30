package com.example.administrator.expressuserclient.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.expressuserclient.commonUtil.DataLoader;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.config.APIConfig;
import com.example.administrator.expressuserclient.contract.SiteListActivityContract;
import com.example.administrator.expressuserclient.http.API;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestCllBack;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestUtil;

import java.util.HashMap;
import java.util.Map;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;
import static com.example.administrator.expressuserclient.R.id.map;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public class SiteListActivityModel implements SiteListActivityContract.Model {
    private RequestQueue queue;


    @Override
    public void loadSiteList(Context context, double latitude, double longitude, final DataLoader dataload) {
        queue = Volley.newRequestQueue(context);
        Map<String, String> map = new HashMap<>();
        map.put("key", "4e0f85e120108994af79bfebb175b85b");
        map.put("location", String.valueOf(latitude) + "," + String.valueOf(longitude));
        map.put("keywords", "快递");
        map.put("types", "");
        map.put("radius", "8000");
        map.put("offset", "20");
        map.put("page", "1");
        map.put("extensions", "base");
        queue.add(VolleyRequestUtil.RequestWithParams(APIConfig.POI, map, new VolleyRequestCllBack() {
            @Override
            public void onSuccess(String result) {
                dataload.getData(result);
            }

            @Override
            public void onError(String error) {
                dataload.getData("数据获取错误！");
                ToastUtil.showToastError("数据获取错误！");
            }
        }));
    }
}
