package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/18/018.
 */

public class DeliverHistory {

    /**
     * id : 1
     * uid : 1
     * pid : 11
     * time : 2018-08-18 15:37:18
     */

    private int id;
    private String time;
    private String count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
