package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/3.
 */

public class OrderGson {


    /**
     * id : 11
     * startlocation : 长沙
     * ordernum : 421313213
     * username : 毛泽东
     * usertel : 1454565615
     * idcard : 430223199711157474
     * userid : 1
     * endlocation : 十三陵镇西山口村
     * createtime : 2018-08-23 00:15:38
     * longitude : 121.76896
     * latitude : 30.745759
     */

    private int id;
    private String startlocation;
    private String ordernum;
    private String username;
    private String usertel;
    private String idcard;
    private String userid;
    private String endlocation;
    private String createtime;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEndlocation() {
        return endlocation;
    }

    public void setEndlocation(String endlocation) {
        this.endlocation = endlocation;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
