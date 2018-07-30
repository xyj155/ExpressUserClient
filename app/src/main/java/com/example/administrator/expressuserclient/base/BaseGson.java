package com.example.administrator.expressuserclient.base;

import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */

public class BaseGson<T> {

    /**
     * success : true
     * code : 200
     * msg : 登陆成功
     */

    private boolean success;
    private int code;
    private String msg;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
