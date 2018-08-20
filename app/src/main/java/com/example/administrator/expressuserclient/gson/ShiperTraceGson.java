package com.example.administrator.expressuserclient.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/8/20/020.
 */

public class ShiperTraceGson <T>{

    /**
     * shipname : 圆通速递
     * shipcode : YTO
     * shiptrace : [{"AcceptStation":"【浙江省金华市义乌市江东南下朱公司】 已收件","AcceptTime":"2018-08-12 18:47:12"},{"AcceptStation":"【浙江省金华市义乌市江东南下朱公司】 已打包","AcceptTime":"2018-08-12 18:50:24"},{"AcceptStation":"【浙江省金华市义乌市江东南下朱公司】 已发出 下一站 【义乌转运中心】","AcceptTime":"2018-08-12 18:54:32"},{"AcceptStation":"【义乌转运中心】 已收入","AcceptTime":"2018-08-12 19:52:01"},{"AcceptStation":"【义乌转运中心】 已发出 下一站 【嘉兴转运中心】","AcceptTime":"2018-08-12 19:55:44"},{"AcceptStation":"【嘉兴转运中心】 已收入","AcceptTime":"2018-08-13 03:32:13"},{"AcceptStation":"【嘉兴转运中心】 已发出 下一站 【浙江省嘉兴市秀洲公司】","AcceptTime":"2018-08-13 03:41:42"},{"AcceptStation":"【浙江省嘉兴市秀洲公司】 已收入","AcceptTime":"2018-08-13 05:25:24"},{"AcceptStation":"【浙江省嘉兴市秀洲公司】 派件人: 郑巧波 派件中 派件员电话17357383132","AcceptTime":"2018-08-13 06:37:19"},{"AcceptStation":"快件已由嘉院梁林（凭证件取）公寓三菜鸟驿站代收，请及时取件，如有疑问请联系18358345866","AcceptTime":"2018-08-13 09:27:03"},{"AcceptStation":"客户 签收人: 梁林菜鸟驿站 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-08-13 13:00:44"}]
     */

    private String shipname;
    private String shipcode;
    private List<T> shiptrace;

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }

    public String getShipcode() {
        return shipcode;
    }

    public void setShipcode(String shipcode) {
        this.shipcode = shipcode;
    }

    public List<T> getShiptrace() {
        return shiptrace;
    }

    public void setShiptrace(List<T> shiptrace) {
        this.shiptrace = shiptrace;
    }

}

