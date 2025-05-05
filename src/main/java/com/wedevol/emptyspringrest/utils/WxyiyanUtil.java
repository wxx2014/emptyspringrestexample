package com.wedevol.emptyspringrest.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wedevol.emptyspringrest.entity.WxyyReqBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WxyiyanUtil {

//    @Value(value = "${wenxin.api-key}")
    private static String apiKey;

//    @Value(value = "${wenxin.url}")
    private static String url;

//    @Value(value = "${wenxin.model}")
    private static String model;



    public static Request bulidRequest(RequestBody body){
        return new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("appid", "")
                .addHeader("Authorization", "Bearer "+apiKey)
                .build();
    }


    public static String getResultByResp(Response response) throws IOException {
        assert response.body() != null;
        String bodyJsonStr = response.body().string();
        Map bodyMap = JSON.parseObject(bodyJsonStr, Map.class);
        JSONArray choices = (JSONArray) bodyMap.get("choices");
        JSONObject choiceJson = (JSONObject)choices.get(0);
        JSONObject message = (JSONObject)choiceJson.get("message");

        String role = (String)message.get("role");
        String resContext =  (String)message.get("content");
        return resContext;
    }

    public static @NotNull String getReqBodyStr(List<WxyyReqBody.Message> messages,WxyyReqBody.WebSearch web_search) {
        WxyyReqBody wxyyReqBody = new WxyyReqBody(model,messages,web_search);
        String wxyyReqBodyStr = JSON.toJSONString(wxyyReqBody);
        return wxyyReqBodyStr;
    }


    @Value(value = "${wenxin.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value(value = "${wenxin.model}")
    public void setModel(String model) {
        this.model = model;
    }

    @Value(value = "${wenxin.api-key}")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
