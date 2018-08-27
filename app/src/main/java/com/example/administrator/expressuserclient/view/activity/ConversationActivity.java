package com.example.administrator.expressuserclient.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.adapter.ConversationAdapter;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.commonUtil.IMUtils;
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
        mConversation = Conversation.createSingleConversation("456789");
        LinearLayoutManager manager = new LinearLayoutManager(ConversationActivity.this);
        manager.setStackFromEnd(true);
        ryContact.setLayoutManager(manager);
        ryContact.setAdapter(mChatAdapter);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        mChatAdapter.bindToRecyclerView(ryContact);

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
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString();
                message1 = IMUtils.sendTextMessage(mConversation, content);
                etContent.setText("");
                messages.add(message1);
                presenter.sendMessage(message1);
                presenter.messageToEntity(messages);

            }
        });
    }

}
