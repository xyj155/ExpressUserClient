package com.example.administrator.expressuserclient.contract.message;

import com.example.administrator.expressuserclient.entity.ConversationEntity;

import java.util.List;

import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2018/8/26/026.
 */

public interface ConversationActivityContract {
    interface Model {

    }

    interface View {
        /**
         * 刷新消息
         */
        void refreshMessage();
        /**
         * 获取会话
         */
        void getConversation(List<ConversationEntity> conversationEntities);
        /**
         * 登录是否成功
         */
        void loginSuccess();

    }

    interface Presenter {
        /**
         * 发送消息
         *
         * @param message
         */
        void sendMessage(Message message);
        /**
         * 会话列表
         *
         * @param conversations
         */
        void conversionToUser(List<Conversation> conversations);

        void messageToEntity(List<Message> message);
        /**
         * im登录
         */
        void login();
    }
}
