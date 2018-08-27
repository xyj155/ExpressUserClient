package com.example.administrator.expressuserclient.adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.entity.ConversationEntity;

import java.util.List;

import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Message;



/**
 * Created by Administrator on 2018/7/11.
 */
public class ConversationAdapter extends BaseMultiItemQuickAdapter<ConversationEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ConversationAdapter(List<ConversationEntity> data) {
        super(data);
        addItemType(ConversationEntity.TYPE_SERVICES_MESSAGE, R.layout.conversation_ry_item_chat_chatfriends);
        addItemType(ConversationEntity.TYPE_CLIENT_MESSAGE, R.layout.conversation_ry_item_chat_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConversationEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case ConversationEntity.TYPE_SERVICES_MESSAGE:
                //客服消息
                break;
            case ConversationEntity.TYPE_CLIENT_MESSAGE:
                //客户消息
                break;
            default:
        }
        Message data = item.getData();
        //时间
        String time = String.format("%tF %tT", data.getCreateTime(), data.getCreateTime());
        //用户名
        String fromName = data.getFromName();

        //获取内容
        MessageContent messageContent = data.getContent();
        TextContent textContent = (TextContent) messageContent;
        helper.setText(R.id.tv_main_item_content,textContent.getText());
//
//        //设置不可见
//        helper.getView(R.id.tv_main_item_content).setVisibility(View.GONE);
//        helper.getView(R.id.iv_main_item_content).setVisibility(View.GONE);
//        helper.getView(R.id.ll_main_item_content).setVisibility(View.GONE);
//        switch (messageContent.getContentType()) {
//            case text:
//                TextContent textContent = (TextContent) messageContent;
//                helper.setText(R.id.tv_main_item_content, textContent.getText())
//                        .setVisible(R.id.tv_main_item_content, true);
//                break;
//            case image:
//                //处理图片消息
//                ImageContent imageContent = (ImageContent) messageContent;
//                //图片本地地址
//                imageContent.getLocalPath();
//                //图片对应缩略图的本地地址
//                String thumbnailPath = imageContent.getLocalThumbnailPath();
////                helper.setVisible(R.id.iv_main_item_content, true);
//
////                BindAdapter.loadImage(helper.getView(R.id.iv_main_item_content), thumbnailPath);
//                break;
//            case voice:
////                //处理语音消息
////                VoiceContent voiceContent = (VoiceContent) messageContent;
////                voiceContent.getLocalPath();
////                //语音文件本地地址
////                int duration = voiceContent.getDuration();
////                //语音文件时长
////                helper.setVisible(R.id.ll_main_item_content, true)
////                        .setText(R.id.tv_main_item_duration, String.format("%d'", duration));
//                break;
//            case custom:
//                //处理自定义消息
//                CustomContent customContent = (CustomContent) messageContent;
//                //获取自定义的值
//                customContent.getNumberValue("custom_num");
//                customContent.getBooleanValue("custom_boolean");
//                customContent.getStringValue("custom_string");
//                break;
//            default:
//        }

//        //设置时间
//        helper.setText(R.id.tv_item_main_time, time)
//                .setText(R.id.tv_main_item_name, fromName);
    }


}