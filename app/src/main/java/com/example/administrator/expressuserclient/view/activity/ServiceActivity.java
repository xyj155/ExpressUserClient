package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.adapter.ChatAdapter;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.contract.user.ServiceActivityContract;
import com.example.administrator.expressuserclient.entity.ChatEntity;
import com.example.administrator.expressuserclient.gson.TurLingGson;
import com.example.administrator.expressuserclient.presenter.user.ServiceActivityPresenter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity implements ServiceActivityContract.View {


    @InjectView(R.id.ry_contact)
    RecyclerView ryContact;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_send)
    ImageView btnSend;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_callback)
    TextView tvCallback;
    private ServiceActivityPresenter presenter = new ServiceActivityPresenter(this);
    private BaseMultiItemQuickAdapter<ChatEntity, BaseViewHolder> mChatAdapter;
    private List<ChatEntity> data = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.activity_service;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        showmDialog("正在连接中....");
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolBarSubTitle("和客服对话中...")
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        LinearLayoutManager manager = new LinearLayoutManager(ServiceActivity.this);
        manager.setStackFromEnd(true);
        ryContact.setLayoutManager(manager);
    }

    @OnClick({R.id.btn_send, R.id.tv_callback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_callback:
                startActivityForResult(new Intent(ServiceActivity.this, FeedbackActivity.class), 0);
                break;
            case R.id.btn_send:
                try {
                    presenter.chatWithRobot(etContent.getText().toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                data.add(ChatEntity.client(etContent.getText().toString()));
                etContent.setText("");
                break;
        }

    }

    @Override
    public void showLoading(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideLoading() {
//        dialog.dismiss();
    }

    @Override
    public void loadContent(TurLingGson turLingGson) {
        Log.i(TAG, "loadContent: " + turLingGson.getText());
        data.add(ChatEntity.service(turLingGson));
        mChatAdapter = new ChatAdapter(data);
        ryContact.setAdapter(mChatAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                finish();
                break;
        }
    }
}
