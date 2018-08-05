package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/4/004.
 */

public class PackageSiteList {

    /**
     * longitude : 120.768564
     * latitude : 30.755759
     * endlocation : 颐和园路5号北京大学镜春园78号院附近
     * usertel : 1737413128
     */
private String username;

    public PackageSiteList(String username, double longitude, double latitude, String endlocation, String usertel) {
        this.username = username;
        this.longitude = longitude;
        this.latitude = latitude;
        this.endlocation = endlocation;
        this.usertel = usertel;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private double longitude;
    private double latitude;
    private String endlocation;
    private String usertel;

    public PackageSiteList() {
    }

    public PackageSiteList(double longitude, double latitude, String endlocation, String usertel) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.endlocation = endlocation;
        this.usertel = usertel;
    }

    @Override
    public String toString() {
        return "PackageSiteList{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", endlocation='" + endlocation + '\'' +
                ", usertel='" + usertel + '\'' +
                '}';
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

    public String getEndlocation() {
        return endlocation;
    }

    public void setEndlocation(String endlocation) {
        this.endlocation = endlocation;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }
}
