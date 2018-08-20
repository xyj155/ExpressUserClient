package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/3.
 */

public class OrderGson {


    /**
     * id : 1
     * ordernum : 421313213
     * date : 2018-08-16T08:44:40.000+0000
     * latintude : 30.745759
     * longtitude : 120.76896
     * username : 毛泽东
     * servicetime : 301
     * endtime : 305
     * startlocation : 长沙
     * endlocation : 十三陵镇西山口村
     * uid : 0
     */

    private int id;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String ordernum;
    private String date;
    private double latintude;
    private double longtitude;
    private String username;
    private int servicetime;
    private int endtime;
    private String startlocation;
    private String endlocation;
    private int uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatintude() {
        return latintude;
    }

    public void setLatintude(double latintude) {
        this.latintude = latintude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getServicetime() {
        return servicetime;
    }

    public void setServicetime(int servicetime) {
        this.servicetime = servicetime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getEndlocation() {
        return endlocation;
    }

    public void setEndlocation(String endlocation) {
        this.endlocation = endlocation;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
