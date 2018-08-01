package com.example.administrator.expressuserclient.gson;

/**
 * Created by Administrator on 2018/8/1.
 */

public class PushGson {

    /**
     * id : 1
     * title : 快递小哥快递员助手更新了
     * content : 快递小哥在360APP市场已经上线，快来更新吧！！！
     * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533143141463&di=d651f021558ba7c9989dac61bf148b48&imgtype=0&src=http%3A%2F%2Fm.365azw.com%2FAttachments%2Fshare%2F201410%2F5437938814de5.jpg
     * date : 2018-08-01 22:18:14
     */

    private int id;
    private String title;
    private String content;
    private String pic;
    private String date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
