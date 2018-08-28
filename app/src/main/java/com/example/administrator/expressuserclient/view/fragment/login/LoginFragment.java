package com.example.administrator.expressuserclient.view.fragment.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.contract.login.LoginFragmentContract;
import com.example.administrator.expressuserclient.presenter.login.LoginFragmentPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/28/028.
 */

public class LoginFragment extends BaseFragment implements LoginFragmentContract.View {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    private LoginFragmentContract.Presenter loginActivityPresenter = new LoginFragmentPresenter(this);

    @Override
    protected int setLayoutResourceID() {
        return R.layout.vp_login_item1;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "font.ttf");
        tvTitle.setTypeface(typeFace);
    }

    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginActivityPresenter.loginWithUserName(getActivity(), etUsername.getText().toString(), etPassword.getText().toString());
                break;

        }
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void showDialog(String msg) {
        showFragmentDialog(msg);
    }

    @Override
    public void hideDialog() {
        hideFragmentDialog();
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
