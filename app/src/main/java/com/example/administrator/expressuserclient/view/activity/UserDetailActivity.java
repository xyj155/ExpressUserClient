package com.example.administrator.expressuserclient.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.user.UserDetailActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.presenter.user.UserDetailActivityPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserDetailActivity extends BaseActivity implements UserDetailActivityContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_identity)
    TextView tvIdentity;
    @InjectView(R.id.tv_ltd)
    TextView tvLtd;
    @InjectView(R.id.tv_tel)
    TextView tvTel;
    private UserDetailActivityPresenter userDetailActivityPresenter = new UserDetailActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_user_dtail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        userDetailActivityPresenter.queryUserInfor(String.valueOf(sp.getInt("id", 0)));
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
    public void getUserInfor(BaseGson<UserGson> userGsonBaseGson) {
        tvIdentity.setText(userGsonBaseGson.getData().get(0).getIdentity()==null?"身份： "+"普通用户":"身份： "+userGsonBaseGson.getData().get(0).getIdentity());
        tvTel.setText(userGsonBaseGson.getData().get(0).getUsertel()==null?"联系方式： "+"你还没有绑定手机号码":"联系方式： "+userGsonBaseGson.getData().get(0).getUsertel());
        tvTel.setTextColor(userGsonBaseGson.getData().get(0).getUsertel()==null?getResources().getColor(R.color.crime):getResources().getColor(R.color.black));
        tvUsername.setText(userGsonBaseGson.getData().get(0).getUsername()==null?"用户名： "+"":"用户名： "+userGsonBaseGson.getData().get(0).getUsername());
        tvLtd.setText(userGsonBaseGson.getData().get(0).getCompany()==null?"所在公司： "+"默认":"所在公司： "+userGsonBaseGson.getData().get(0).getCompany());
    }
}
