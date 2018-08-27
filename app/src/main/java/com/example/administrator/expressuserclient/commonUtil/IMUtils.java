package com.example.administrator.expressuserclient.commonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.exceptions.JMFileSizeExceedException;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2018/7/11.
 */

public class IMUtils {
    /**
     * 用户已存在
     */
    public static final int CODE_USER_EXIST = 898001;

    /**
     * 操作成功
     */
    public static final int CODE_SUCCESS = 0;


    /**
     * 用户注册
     *
     * @param username
     * @param password
     */
    public static void register(String username, String password, BasicCallback callback) {
        JMessageClient.register(username, password, callback);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param callback
     */
    public static void login(String username, String password, BasicCallback callback) {
        JMessageClient.login(username, password, callback);
    }

    /**
     * 创建会话
     *
     * @param userName
     */
    public static Conversation cretaeConversation(String userName) {
        return Conversation.createSingleConversation(userName);
    }

    /**
     * 获取未读消息数
     *
     * @param conversation
     * @return
     */
    public static int getUnreadCount(Conversation conversation) {
        return conversation.getUnReadMsgCnt();
    }

    /**
     * 创建文字消息
     *
     * @param userName
     * @param message
     */
    public static Message createTextMessage(String userName, String message) {
        return JMessageClient.createSingleTextMessage(userName, message);
    }

    /**
     * 发送文字消息
     *
     * @param conversation
     * @param message
     * @return
     */
    public static Message sendTextMessage(Conversation conversation, String message) {
        return conversation.createSendMessage(new TextContent(message));
    }

    /**
     * 创建语音消息
     *
     * @param userName
     * @param file
     * @param duration
     */
    public static Message createVoiceMessage(String userName, File file, int duration) throws
            FileNotFoundException {
        return JMessageClient.createSingleVoiceMessage(userName, file, duration);
    }

    /**
     * 创建位置消息
     *
     * @param userName  聊天对象的用户名
     * @param latitude  纬度信息
     * @param longitude 经度信息
     * @param scale     地图缩放比例
     * @param address   详细地址信息
     * @return
     * @throws FileNotFoundException
     */
    public static Message createLcationMessage(String userName, double latitude, double
            longitude, int scale, String address) throws
            FileNotFoundException {
        return JMessageClient.createSingleLocationMessage(userName, "", latitude, longitude,
                scale, address);
    }

    /**
     * 创建文件消息
     *
     * @param username
     * @param file
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws JMFileSizeExceedException
     */
    public static Message createFileMessage(String username, File file, String
            fileName) throws FileNotFoundException, JMFileSizeExceedException {
        return JMessageClient.createSingleFileMessage(username, "", file, fileName);
    }

    /**
     * 创建自定义消息
     *
     * @param userName
     * @param valuesMap
     * @return
     */
    public static Message createCustomMessage(String userName, Map<? extends String, ? extends
                String> valuesMap) {
        return JMessageClient.createSingleCustomMessage(userName, valuesMap);
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public static void sendMessage(Message message) {
        JMessageClient.sendMessage(message);
    }

    /**
     * 获取所有消息
     *
     * @param conversation
     * @return
     */
    public static List<Message> allMessage(Conversation conversation) {
        return conversation.getAllMessage();
    }

    /**
     * 获取单个会话
     *
     * @param userName
     * @return
     */
    public static Conversation getConversation(String userName) {
        return JMessageClient.getSingleConversation(userName);
    }

    /**
     * 获取会话列表
     *
     * @return
     */
    public static List<Message> getMessage(Conversation conversation) {
        return conversation.getAllMessage();
    }

    /**
     * 操作是否成功
     *
     * @param code
     * @return
     */
    public static boolean isSuccess(int code) {
        return 0 == code;
    }

    /**
     * 获取消息列表,按时间降序排列
     *
     * @param conversation
     * @param offset
     * @param limit
     * @return
     */
    public static List<Message> getMessageNewest(Conversation conversation, int offset, int limit) {
        return conversation.getMessagesFromNewest(offset, limit);
    }
}