package com.example.administrator.expressuserclient.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.entity.ChatEntity;

import java.util.List;


/**
 * Created by Administrator on 2018/7/11.
 */
public class ChatAdapter extends BaseMultiItemQuickAdapter<ChatEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    public ChatAdapter(List<ChatEntity> data) {
        super(data);
        addItemType(ChatEntity.TYPE_SERVICES_MESSAGE, R.layout.service_recycle_item_chat_chatfriends);
        addItemType(ChatEntity.TYPE_CLIENT_MESSAGE, R.layout.service_recycle_item_chat_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ChatEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case ChatEntity.TYPE_SERVICES_MESSAGE:
                helper.setText(R.id.tv_main_item_content, item.getData().getText());
                break;
            case ChatEntity.TYPE_CLIENT_MESSAGE:
                helper.setText(R.id.tv_main_item_content, item.getInputText());
                break;
            default:
        }

    }


}