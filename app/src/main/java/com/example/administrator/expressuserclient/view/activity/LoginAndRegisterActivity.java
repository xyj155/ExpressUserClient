package com.example.administrator.expressuserclient.view.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.contract.login.LoginActivityContract;
import com.example.administrator.expressuserclient.presenter.login.LoginActivityPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginAndRegisterActivity extends BaseActivity implements LoginActivityContract.View {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.img_finish)
    ImageView imgFinish;
    private LoginActivityContract.Presenter loginActivityPresenter = new LoginActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_login_and_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
        tvTitle.setTypeface(typeFace);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_login,R.id.img_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginActivityPresenter.loginWithUserName(LoginAndRegisterActivity.this, etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.img_finish:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
