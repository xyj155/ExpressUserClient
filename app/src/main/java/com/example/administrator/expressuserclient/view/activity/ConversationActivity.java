package com.example.administrator.expressuserclient.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.adapter.ConversationAdapter;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.commonUtil.IMUtils;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.message.ConversationActivityContract;
import com.example.administrator.expressuserclient.entity.ConversationEntity;
import com.example.administrator.expressuserclient.presenter.message.ConversationActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class ConversationActivity extends BaseActivity implements ConversationActivityContract.View {

    @InjectView(R.id.ry_contact)
    RecyclerView ryContact;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_send)
    Button btnSend;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private List<Message> messages = new ArrayList<>();
    private ConversationAdapter mChatAdapter = new ConversationAdapter(null);
    private Conversation mConversation;
    private Message message1;
    private ConversationActivityPresenter presenter = new ConversationActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_conversation;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        presenter.login();


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
    public void refreshMessage() {

    }

    @Override
    public void getConversation(List<ConversationEntity> conversationEntities) {

        ryContact.smoothScrollToPosition(conversationEntities.size());
        mChatAdapter.replaceData(conversationEntities);
    }


    @Override
    public void loginSuccess() {
        showmDialog("数据加载中...");
        mConversation = Conversation.createSingleConversation(getIntent().getStringExtra("username"));
        if (mConversation != null) {
            List<Message> allMessage = mConversation.getAllMessage();
            if (allMessage != null) {
                messages.addAll(allMessage);
                presenter.messageToEntity(messages);
                hidemDialog();
                Log.i(TAG, "initView: " + allMessage);
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(ConversationActivity.this);
        manager.setStackFromEnd(true);
        ryContact.setLayoutManager(manager);

        ryContact.setAdapter(mChatAdapter);
        mChatAdapter.bindToRecyclerView(ryContact);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString();
                if (mConversation != null) {
                    if (!content.isEmpty()) {
                        message1 = IMUtils.sendTextMessage(mConversation, content);
                        etContent.setText("");
                        messages.add(message1);
                        presenter.sendMessage(message1);
                        presenter.messageToEntity(messages);
                    } else {
                        ToastUtil.showToastInfor("请输入发送信息！");
                    }

                }
            }
        });
    }

    @Override
    public void loadHistoryMessage(List<Message> messages) {

    }

    @Override
    public void showDialog(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideDialog() {
        hidemDialog();
    }

}
