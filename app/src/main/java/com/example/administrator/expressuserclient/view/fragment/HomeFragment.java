package com.example.administrator.expressuserclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.youth.banner.Banner;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.expressuserclient.config.SysConfig.REQUEST_QR_CODE;

/**
 * Created by Administrator on 2018/7/29.
 */

public class HomeFragment extends BaseFragment {



    @InjectView(R.id.btn_scan)
    ImageView btnScan;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.btn_packet_search)
    TextView btnPacketSearch;
    @InjectView(R.id.btn_packet_history)
    TextView btnPacketHistory;
    @InjectView(R.id.btn_packet_deliver)
    TextView btnPacketDeliver;
    @InjectView(R.id.btn_deliver_points)
    TextView btnDeliverPoints;
    @InjectView(R.id.home_banner)
    Banner homeBanner;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

    }

    @Override
    protected void setUpData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_scan, R.id.btn_packet_search, R.id.btn_packet_history, R.id.btn_packet_deliver, R.id.btn_deliver_points})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_QR_CODE);
                break;
            case R.id.btn_packet_search:
                break;
            case R.id.btn_packet_history:
                break;
            case R.id.btn_packet_deliver:
                break;
            case R.id.btn_deliver_points:
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
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        }
    }

}
