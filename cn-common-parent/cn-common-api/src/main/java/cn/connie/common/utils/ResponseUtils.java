package cn.connie.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class ResponseUtils {

    public static String toSuccessResponse(Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", obj);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
    }

    public static String toErrorResponse(String message, String messageText, String messageUrl) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", message);
        jsonObject.put("msgText", messageText);
        jsonObject.put("msgUrl", messageUrl);
        return jsonObject.toJSONString();
    }

    public static String toErrorResponse(String message, String tips) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", message);
        jsonObject.put("msgText", tips);
        return jsonObject.toJSONString();
    }
}
