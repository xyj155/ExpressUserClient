package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.commonUtil.SystemUtil;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.user.FeedbackActivityContract;
import com.example.administrator.expressuserclient.presenter.user.FeedbackActivityPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity implements FeedbackActivityContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_feedback)
    EditText etFeedback;
    @InjectView(R.id.btn_feedback)
    Button btnFeedback;
    private FeedbackActivityPresenter presenter = new FeedbackActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
    }

    @OnClick(R.id.btn_feedback)
    public void onViewClicked() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        presenter.createNewFeed(
                SystemUtil.getDeviceBrand(),
                sp.getString("username", ""),
                String.valueOf(sp.getInt("id", 8)),
                etFeedback.getText().toString()
        );
    }

    @Override
    public void showDialog(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideDialog() {
        hidemDialog();
    }

    @Override
    public void isSuccess(boolean success) {
        if (success) {
            ToastUtil.showToastSuccess("反馈提交成功！");
            Intent intent = new Intent(FeedbackActivity.this, SettingActivity.class);
            setResult(0, intent);
            finish();
        } else {
            ToastUtil.showToastError("反馈提交失败！");
        }
    }
}
