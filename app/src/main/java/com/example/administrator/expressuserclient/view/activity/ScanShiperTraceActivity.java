package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.ShiperTraceActivityContract;
import com.example.administrator.expressuserclient.gson.ShiperTraceGson;
import com.example.administrator.expressuserclient.gson.ShiptraceBean;
import com.example.administrator.expressuserclient.presenter.home.ShiperTraceActivityPresenter;
import com.example.administrator.expressuserclient.weight.UnderLineLinearLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class ScanShiperTraceActivity extends BaseActivity implements ShiperTraceActivityContract.View {

    private UnderLineLinearLayout mUnderLineLinearLayout;
    private SmartRefreshLayout smart_layout;
    private ShiperTraceActivityPresenter presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_scan_shiper_trace;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Intent intent=getIntent();
        final String code = intent.getStringExtra("code");
        Log.i(TAG, "initView: "+code);
        mUnderLineLinearLayout = (UnderLineLinearLayout) findViewById(R.id.underline_layout);
        smart_layout = (SmartRefreshLayout) findViewById(R.id.smart_layout);
        presenter = new ShiperTraceActivityPresenter(this);

        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        smart_layout.autoRefresh();
        smart_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart_layout.finishRefresh();
                presenter.loadFromShipe(code);
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public void loadFromShiper(BaseGson<ShiperTraceGson<ShiptraceBean>> baseGson) {
        for (int i = 0; i < baseGson.getData().get(0).getShiptrace().size(); i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.ry_item_shipertrace_layout, mUnderLineLinearLayout, false);
            ((TextView) v.findViewById(R.id.tx_action)).setText(baseGson.getData().get(0).getShiptrace().get(i).getAcceptStation());
            ((TextView) v.findViewById(R.id.tx_action_time)).setText(baseGson.getData().get(0).getShiptrace().get(i).getAcceptTime());
            ((TextView) v.findViewById(R.id.tx_action_status)).setText("完成");
            mUnderLineLinearLayout.addView(v);
        }
    }

    @Override
    public void showLoading(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideLoading() {
        hidemDialog();
    }


}
