package com.example.administrator.expressuserclient.commonUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtil {
    // 定义一个json对象
    public static Gson gson = new Gson();

    // 将json数据转换成对应的类型数据
    public static <T> T parseJsonWithClass(String jsonData, Class<T> type) {
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    // 将其他类型数据转换成json数据
    public static String parseToJson(Object jsonData) {
        String result = gson.toJson(jsonData);
        return result;
    }

    // 将json数据转换成list集合
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }
}