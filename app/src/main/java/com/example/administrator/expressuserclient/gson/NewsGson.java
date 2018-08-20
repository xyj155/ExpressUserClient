package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class NewsGson {

    /**
     * id : 1
     * title : xyj
     * url : sd
     * content : ds
     * time : 0000-00-00 00:00:00
     * pic : ds
     */

    private int id;
    private String title;
    private String url;
    private String content;
    private String time;
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
