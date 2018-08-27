package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.ShiperTraceActivityContract;
import com.example.administrator.expressuserclient.gson.ShiperTraceGson;
import com.example.administrator.expressuserclient.gson.ShiptraceBean;
import com.example.administrator.expressuserclient.presenter.home.ShiperTraceActivityPresenter;
import com.example.administrator.expressuserclient.weight.UnderLineLinearLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;

import static com.example.administrator.expressuserclient.config.SysConfig.REQUEST_QR_CODE;

public class ShiperTraceActivity extends BaseActivity implements ShiperTraceActivityContract.View {

    @InjectView(R.id.btn_scan)
    ImageView btnScan;
    @InjectView(R.id.et_express)
    EditText etExpress;
    @InjectView(R.id.btn_search)
    Button btnSearch;

    private UnderLineLinearLayout mUnderLineLinearLayout;
    private SmartRefreshLayout smart_layout;
    private ShiperTraceActivityPresenter presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_shiper_trace;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.btn_scan, R.id.et_express, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                Intent intent = new Intent(ShiperTraceActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_QR_CODE);
                break;
            case R.id.btn_search:
                if (etExpress.getText().toString().isEmpty()) {
                    ToastUtil.showToastError("输入不可为空！");
                } else {
                    mUnderLineLinearLayout.removeAllViews();
                    mUnderLineLinearLayout.removeAllViewsInLayout();
                    presenter.loadFromShipe(etExpress.getText().toString());
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK
                && requestCode == REQUEST_QR_CODE
                && data != null) {
            String result = data.getStringExtra("result");
            mUnderLineLinearLayout.removeAllViews();
            mUnderLineLinearLayout.removeAllViewsInLayout();
            presenter.loadFromShipe(result);
        }
    }

}
