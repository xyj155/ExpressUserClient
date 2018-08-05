package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.gson.POIGson;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestCllBack;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestUtil;
import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExpressStationDetailActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_distance)
    TextView tvDistance;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.tv_type)
    TextView tvType;
    @InjectView(R.id.ry_detail)
    RecyclerView ryDetail;
    private RequestQueue queue;

    @Override
    public int intiLayout() {
        return R.layout.activity_express_station_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        queue = Volley.newRequestQueue(ExpressStationDetailActivity.this);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        Intent intent = getIntent();
        tvType.setText(intent.getStringExtra("type"));
        tvName.setText(intent.getStringExtra("name"));
        tvDistance.setText(intent.getStringExtra("distance"));
        tvAddress.setText(intent.getStringExtra("address"));
        String location = intent.getStringExtra("location");
        queue.add(VolleyRequestUtil.Request("https://restapi.amap.com/v3/place/around?key=4e0f85e120108994af79bfebb175b85b&location=" + location + "&keywords=快递&types=&radius=1000&offset=5&page=1&extensions=base", new VolleyRequestCllBack() {
            @Override
            public void onSuccess(String result) {
                String aNull = result.replace("[]", "null");
                Gson gson = new Gson();
                POIGson poiGson = gson.fromJson(aNull, POIGson.class);
                ryDetail.setLayoutManager(new LinearLayoutManager(ExpressStationDetailActivity.this));
                ryDetail.setAdapter(new ExpressSiteListActivity.SiteListAdapter(ExpressStationDetailActivity.this, poiGson.getPois()));
            }

            @Override
            public void onError(String error) {

            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
