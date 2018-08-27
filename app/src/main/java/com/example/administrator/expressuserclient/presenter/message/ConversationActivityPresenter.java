package com.example.administrator.expressuserclient.presenter.message;

import android.util.Log;

import com.example.administrator.expressuserclient.commonUtil.IMUtils;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.message.ConversationActivityContract;
import com.example.administrator.expressuserclient.entity.ConversationEntity;
import com.example.administrator.expressuserclient.model.message.ConversationActivityModel;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

import static cn.jpush.im.android.tasks.GetUserInfoListTask.IDType.username;

/**
 * Created by Administrator on 2018/8/26/026.
 */

public class ConversationActivityPresenter implements ConversationActivityContract.Presenter {
    private ConversationActivityContract.View view;
    private ConversationActivityModel model = new ConversationActivityModel();

    public ConversationActivityPresenter(ConversationActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void sendMessage(Message message) {
        if (message == null) {
            ToastUtil.showToastError("发送失败");
            return;
        }
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int code, String s) {
                if (code == IMUtils.CODE_SUCCESS) {

                } else {
                    ToastUtil.showToastError("发送失败");
                }
            }
        });
        JMessageClient.sendMessage(message);

    }

    @Override
    public void conversionToUser(List<Conversation> conversations) {
        conversations = JMessageClient.getConversationList();

    }

    private static final String TAG = "ConversationActivityPre";

    @Override
    public void messageToEntity(List<Message> messages) {
        Log.i(TAG, "messageToEntity: " + messages.size());
        List<ConversationEntity> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getFromName().equals("123456")) {
                list.add(ConversationEntity.client(message));
            } else {
                list.add(ConversationEntity.service(message));
            }
        }
        view.getConversation(list);
    }

    @Override
    public void login() {
        view.showDialog("数据加载中...");
        IMUtils.login("123456", "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, final String s) {
                if (i == 0) {
                    view.hideDialog();
                    view.loginSuccess();
                } else {
                    view.hideDialog();
                    ToastUtil.showToastError("未知错误：请联系开发者,错误详情：" + s);
                }
            }
        });
    }

    @Override
    public void getHistoryMessage() {
        Conversation conversation = JMessageClient.getSingleConversation("456789", "ce96018f1ca252300f934c39");

        List<Message> allMessage = conversation.getAllMessage();
        for (Message m : allMessage) {
            MessageContent content = m.getContent();
            Log.i(TAG, "getHistoryMessage: "+ content.toJson());
        }
    }
}
