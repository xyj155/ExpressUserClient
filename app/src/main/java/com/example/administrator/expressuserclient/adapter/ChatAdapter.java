package com.example.administrator.expressuserclient.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.entity.ServiceEntity;

import java.util.List;


/**
 * Created by Administrator on 2018/7/11.
 */
public class ChatAdapter extends BaseMultiItemQuickAdapter<ServiceEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    public ChatAdapter(List<ServiceEntity> data) {
        super(data);
        addItemType(ServiceEntity.TYPE_SERVICES_MESSAGE, R.layout.ry_item_service_recycle_chat_chatfriends);
        addItemType(ServiceEntity.TYPE_CLIENT_MESSAGE, R.layout.ry_item_service_recycle_chat_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ServiceEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case ServiceEntity.TYPE_SERVICES_MESSAGE:
                helper.setText(R.id.tv_main_item_content, item.getData().getText());
                break;
            case ServiceEntity.TYPE_CLIENT_MESSAGE:
                helper.setText(R.id.tv_main_item_content, item.getInputText());
                break;
            default:
        }

    }


}