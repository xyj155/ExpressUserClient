package com.example.administrator.expressuserclient.view.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.contract.LoginActivityContract;
import com.example.administrator.expressuserclient.presenter.LoginActivityPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityContract.View {

    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    private LoginActivityContract.Presenter loginActivityPresenter = new LoginActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
        tvLogin.setTypeface(typeFace);
    }

    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginActivityPresenter.loginWithUserName(LoginActivity.this, etUsername.getText().toString(), etPassword.getText().toString());
                break;
        }
    }


    @Override
    public void showDialog(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }
}
