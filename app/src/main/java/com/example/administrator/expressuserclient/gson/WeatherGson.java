package com.example.administrator.expressuserclient.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/7/31/031.
 */

public class WeatherGson {

    /**
     * msg : success
     * result : [{"airCondition":"轻度污染","airQuality":{"aqi":105,"city":"通州","district":"通州","fetureData":[{"aqi":94,"date":"2018-08-01","quality":"良"},{"aqi":97,"date":"2018-08-02","quality":"良"},{"aqi":115,"date":"2018-08-03","quality":"轻度污染"},{"aqi":92,"date":"2018-08-04","quality":"良"},{"aqi":106,"date":"2018-08-05","quality":"轻度污染"}],"hourData":[{"aqi":105,"dateTime":"2018-07-31 15:00:00"},{"aqi":139,"dateTime":"2018-07-31 14:00:00"},{"aqi":148,"dateTime":"2018-07-31 13:00:00"},{"aqi":130,"dateTime":"2018-07-31 12:00:00"},{"aqi":113,"dateTime":"2018-07-31 11:00:00"},{"aqi":142,"dateTime":"2018-07-31 10:00:00"},{"aqi":139,"dateTime":"2018-07-31 09:00:00"},{"aqi":120,"dateTime":"2018-07-31 08:00:00"},{"aqi":97,"dateTime":"2018-07-31 07:00:00"},{"aqi":83,"dateTime":"2018-07-31 06:00:00"},{"aqi":79,"dateTime":"2018-07-31 05:00:00"},{"aqi":89,"dateTime":"2018-07-31 04:00:00"},{"aqi":103,"dateTime":"2018-07-31 03:00:00"},{"aqi":103,"dateTime":"2018-07-31 02:00:00"},{"aqi":83,"dateTime":"2018-07-31 01:00:00"},{"aqi":73,"dateTime":"2018-07-31 00:00:00"},{"aqi":70,"dateTime":"2018-07-30 23:00:00"},{"aqi":74,"dateTime":"2018-07-30 22:00:00"},{"aqi":78,"dateTime":"2018-07-30 21:00:00"},{"aqi":77,"dateTime":"2018-07-30 20:00:00"},{"aqi":69,"dateTime":"2018-07-30 19:00:00"},{"aqi":74,"dateTime":"2018-07-30 18:00:00"},{"aqi":92,"dateTime":"2018-07-30 17:00:00"},{"aqi":99,"dateTime":"2018-07-30 16:00:00"}],"no2":11,"pm10":-1,"pm25":50,"province":"北京","quality":"轻度污染","so2":3,"updateTime":"2018-07-31 16:00:00"},"city":"通州","coldIndex":"易发期","date":"2018-07-31","distrct":"通州","dressingIndex":"薄短袖类","exerciseIndex":"不适宜","future":[{"date":"2018-07-31","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"今天","wind":"南风 小于3级"},{"date":"2018-08-01","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"星期三","wind":"东南风 小于3级"},{"date":"2018-08-02","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"星期四","wind":"南风 小于3级"},{"date":"2018-08-03","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"星期五","wind":"东南风 小于3级"},{"date":"2018-08-04","dayTime":"晴","night":"多云","temperature":"34°C / 27°C","week":"星期六","wind":"东南风 小于3级"},{"date":"2018-08-05","dayTime":"多云","night":"雷阵雨","temperature":"34°C / 26°C","week":"星期日","wind":"西南风 小于3级"}],"humidity":"湿度：50%","pollutionIndex":"105","province":"北京","sunrise":"04:49","sunset":"19:46","temperature":"35℃","time":"16:10","updateTime":"20180731163152","washIndex":"","weather":"晴","week":"周二","wind":"东南风2级"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * airCondition : 轻度污染
         * airQuality : {"aqi":105,"city":"通州","district":"通州","fetureData":[{"aqi":94,"date":"2018-08-01","quality":"良"},{"aqi":97,"date":"2018-08-02","quality":"良"},{"aqi":115,"date":"2018-08-03","quality":"轻度污染"},{"aqi":92,"date":"2018-08-04","quality":"良"},{"aqi":106,"date":"2018-08-05","quality":"轻度污染"}],"hourData":[{"aqi":105,"dateTime":"2018-07-31 15:00:00"},{"aqi":139,"dateTime":"2018-07-31 14:00:00"},{"aqi":148,"dateTime":"2018-07-31 13:00:00"},{"aqi":130,"dateTime":"2018-07-31 12:00:00"},{"aqi":113,"dateTime":"2018-07-31 11:00:00"},{"aqi":142,"dateTime":"2018-07-31 10:00:00"},{"aqi":139,"dateTime":"2018-07-31 09:00:00"},{"aqi":120,"dateTime":"2018-07-31 08:00:00"},{"aqi":97,"dateTime":"2018-07-31 07:00:00"},{"aqi":83,"dateTime":"2018-07-31 06:00:00"},{"aqi":79,"dateTime":"2018-07-31 05:00:00"},{"aqi":89,"dateTime":"2018-07-31 04:00:00"},{"aqi":103,"dateTime":"2018-07-31 03:00:00"},{"aqi":103,"dateTime":"2018-07-31 02:00:00"},{"aqi":83,"dateTime":"2018-07-31 01:00:00"},{"aqi":73,"dateTime":"2018-07-31 00:00:00"},{"aqi":70,"dateTime":"2018-07-30 23:00:00"},{"aqi":74,"dateTime":"2018-07-30 22:00:00"},{"aqi":78,"dateTime":"2018-07-30 21:00:00"},{"aqi":77,"dateTime":"2018-07-30 20:00:00"},{"aqi":69,"dateTime":"2018-07-30 19:00:00"},{"aqi":74,"dateTime":"2018-07-30 18:00:00"},{"aqi":92,"dateTime":"2018-07-30 17:00:00"},{"aqi":99,"dateTime":"2018-07-30 16:00:00"}],"no2":11,"pm10":-1,"pm25":50,"province":"北京","quality":"轻度污染","so2":3,"updateTime":"2018-07-31 16:00:00"}
         * city : 通州
         * coldIndex : 易发期
         * date : 2018-07-31
         * distrct : 通州
         * dressingIndex : 薄短袖类
         * exerciseIndex : 不适宜
         * future : [{"date":"2018-07-31","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"今天","wind":"南风 小于3级"},{"date":"2018-08-01","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"星期三","wind":"东南风 小于3级"},{"date":"2018-08-02","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"星期四","wind":"南风 小于3级"},{"date":"2018-08-03","dayTime":"晴","night":"晴","temperature":"35°C / 26°C","week":"星期五","wind":"东南风 小于3级"},{"date":"2018-08-04","dayTime":"晴","night":"多云","temperature":"34°C / 27°C","week":"星期六","wind":"东南风 小于3级"},{"date":"2018-08-05","dayTime":"多云","night":"雷阵雨","temperature":"34°C / 26°C","week":"星期日","wind":"西南风 小于3级"}]
         * humidity : 湿度：50%
         * pollutionIndex : 105
         * province : 北京
         * sunrise : 04:49
         * sunset : 19:46
         * temperature : 35℃
         * time : 16:10
         * updateTime : 20180731163152
         * washIndex :
         * weather : 晴
         * week : 周二
         * wind : 东南风2级
         */

        private String airCondition;
        private AirQualityBean airQuality;
        private String city;
        private String coldIndex;
        private String date;
        private String distrct;
        private String dressingIndex;
        private String exerciseIndex;
        private String humidity;
        private String pollutionIndex;
        private String province;
        private String sunrise;
        private String sunset;
        private String temperature;
        private String time;
        private String updateTime;
        private String washIndex;
        private String weather;
        private String week;
        private String wind;
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public AirQualityBean getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(AirQualityBean airQuality) {
            this.airQuality = airQuality;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public void setPollutionIndex(String pollutionIndex) {
            this.pollutionIndex = pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
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

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class AirQualityBean {
            /**
             * aqi : 105
             * city : 通州
             * district : 通州
             * fetureData : [{"aqi":94,"date":"2018-08-01","quality":"良"},{"aqi":97,"date":"2018-08-02","quality":"良"},{"aqi":115,"date":"2018-08-03","quality":"轻度污染"},{"aqi":92,"date":"2018-08-04","quality":"良"},{"aqi":106,"date":"2018-08-05","quality":"轻度污染"}]
             * hourData : [{"aqi":105,"dateTime":"2018-07-31 15:00:00"},{"aqi":139,"dateTime":"2018-07-31 14:00:00"},{"aqi":148,"dateTime":"2018-07-31 13:00:00"},{"aqi":130,"dateTime":"2018-07-31 12:00:00"},{"aqi":113,"dateTime":"2018-07-31 11:00:00"},{"aqi":142,"dateTime":"2018-07-31 10:00:00"},{"aqi":139,"dateTime":"2018-07-31 09:00:00"},{"aqi":120,"dateTime":"2018-07-31 08:00:00"},{"aqi":97,"dateTime":"2018-07-31 07:00:00"},{"aqi":83,"dateTime":"2018-07-31 06:00:00"},{"aqi":79,"dateTime":"2018-07-31 05:00:00"},{"aqi":89,"dateTime":"2018-07-31 04:00:00"},{"aqi":103,"dateTime":"2018-07-31 03:00:00"},{"aqi":103,"dateTime":"2018-07-31 02:00:00"},{"aqi":83,"dateTime":"2018-07-31 01:00:00"},{"aqi":73,"dateTime":"2018-07-31 00:00:00"},{"aqi":70,"dateTime":"2018-07-30 23:00:00"},{"aqi":74,"dateTime":"2018-07-30 22:00:00"},{"aqi":78,"dateTime":"2018-07-30 21:00:00"},{"aqi":77,"dateTime":"2018-07-30 20:00:00"},{"aqi":69,"dateTime":"2018-07-30 19:00:00"},{"aqi":74,"dateTime":"2018-07-30 18:00:00"},{"aqi":92,"dateTime":"2018-07-30 17:00:00"},{"aqi":99,"dateTime":"2018-07-30 16:00:00"}]
             * no2 : 11
             * pm10 : -1
             * pm25 : 50
             * province : 北京
             * quality : 轻度污染
             * so2 : 3
             * updateTime : 2018-07-31 16:00:00
             */

            private int aqi;
            private String city;
            private String district;
            private int no2;
            private int pm10;
            private int pm25;
            private String province;
            private String quality;
            private int so2;
            private String updateTime;
            private List<FetureDataBean> fetureData;
            private List<HourDataBean> hourData;

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public int getNo2() {
                return no2;
            }

            public void setNo2(int no2) {
                this.no2 = no2;
            }

            public int getPm10() {
                return pm10;
            }

            public void setPm10(int pm10) {
                this.pm10 = pm10;
            }

            public int getPm25() {
                return pm25;
            }

            public void setPm25(int pm25) {
                this.pm25 = pm25;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public int getSo2() {
                return so2;
            }

            public void setSo2(int so2) {
                this.so2 = so2;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public List<FetureDataBean> getFetureData() {
                return fetureData;
            }

            public void setFetureData(List<FetureDataBean> fetureData) {
                this.fetureData = fetureData;
            }

            public List<HourDataBean> getHourData() {
                return hourData;
            }

            public void setHourData(List<HourDataBean> hourData) {
                this.hourData = hourData;
            }

            public static class FetureDataBean {
                /**
                 * aqi : 94
                 * date : 2018-08-01
                 * quality : 良
                 */

                private int aqi;
                private String date;
                private String quality;

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }
            }

            public static class HourDataBean {
                /**
                 * aqi : 105
                 * dateTime : 2018-07-31 15:00:00
                 */

                private int aqi;
                private String dateTime;

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getDateTime() {
                    return dateTime;
                }

                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }
            }
        }

        public static class FutureBean {
            /**
             * date : 2018-07-31
             * dayTime : 晴
             * night : 晴
             * temperature : 35°C / 26°C
             * week : 今天
             * wind : 南风 小于3级
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
    }
}
