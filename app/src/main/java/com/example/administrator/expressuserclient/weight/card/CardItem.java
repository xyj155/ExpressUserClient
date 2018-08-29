package com.example.administrator.expressuserclient.weight.card;


public class CardItem {

    public CardItem(String dayTime, String temperature, String week, String wind) {
        this.dayTime = dayTime;
        this.temperature = temperature;
        this.week = week;
        this.wind = wind;
    }

    /**
     * date : 2018-08-29
     * dayTime : 多云
     * night : 多云
     * temperature : 33°C / 26°C
     * week : 今天
     * wind : 东南风 4～5级
     */


    private String date;
    private String dayTime;
    private String night;
    private String temperature;
    private String week;
    private String wind;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
