package com.example.administrator.expressuserclient.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/7/29.
 */

public class MessageFragment extends BaseFragment {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_message;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

    }

    @Override
    protected void setUpData() {
        toolbar.setSubtitle("消息");
        toolbar.setSubtitleTextColor(0xff000000);
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
}
