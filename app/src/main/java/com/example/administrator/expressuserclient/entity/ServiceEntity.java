package com.example.administrator.expressuserclient.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.expressuserclient.gson.TurLingGson;

/**
 * Created by Administrator on 2018/7/11.
 */

public class ServiceEntity implements MultiItemEntity {

    public static final int TYPE_SERVICES_MESSAGE = 1;

    public static final int TYPE_CLIENT_MESSAGE = 2;

    private int itemType;

    private TurLingGson data;
    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public ServiceEntity(int itemType, TurLingGson data, String inputText) {
        this.itemType = itemType;
        this.data = data;
        this.inputText = inputText;
    }

    public ServiceEntity(int itemType, TurLingGson data) {
        this.itemType = itemType;
        this.data = data;
    }
    public ServiceEntity(int itemType, String inputText) {
        this.itemType = itemType;
        this.inputText = inputText;
    }
    /**
     * @param data
     * @return
     */
    public static ServiceEntity service(TurLingGson data) {
        return new ServiceEntity(TYPE_SERVICES_MESSAGE, data);
    }


    /**
     * @param data
     * @return
     */
    public static ServiceEntity client(String data) {
        return new ServiceEntity(TYPE_CLIENT_MESSAGE, data);
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public TurLingGson getData() {
        return data;
    }

    public void setData(TurLingGson data) {
        this.data = data;
    }
}