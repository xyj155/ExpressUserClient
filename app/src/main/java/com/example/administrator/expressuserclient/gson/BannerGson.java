package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class BannerGson {

    /**
     * id : 1
     * pic1 : d sas d
     * pic2 : das
     * pic3 : da as
     * pic4 : da
     * pic5 : da
     */

    private int id;
    private String pic;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
