package com.wedevol.emptyspringrest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wedevol.emptyspringrest.config.WxyiyanConfigProperties;
import com.wedevol.emptyspringrest.entity.WxyyReqBody;
import com.wedevol.emptyspringrest.service.WxyiyanService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@EnableConfigurationProperties(WxyiyanConfigProperties.class)
public class WxyiyanServiceImpl implements WxyiyanService {

    private static final Logger LOG = LoggerFactory.getLogger(WxyiyanServiceImpl.class);

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private WxyiyanConfigProperties wxyiyanConfigProperties;

    /**
     * 请求内容json字符串
     * @param messages
     * @param web_search
     * @return
     */
    public String getReqBodyStr(List<WxyyReqBody.Message> messages,WxyyReqBody.WebSearch web_search) {
        WxyyReqBody wxyyReqBody = new WxyyReqBody(wxyiyanConfigProperties.getModel(),messages,web_search);
        String wxyyReqBodyStr = JSON.toJSONString(wxyyReqBody);
        return wxyyReqBodyStr;
    }

    /**
     * 构建文心一言请求对象
     * @param body
     * @return
     */
    public Request bulidRequest(RequestBody body){
        return new Request.Builder()
                .url(wxyiyanConfigProperties.getUrl())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("appid", "")
                .addHeader("Authorization", "Bearer "+wxyiyanConfigProperties.getApiKey())
                .build();
    }

    /***
     * 返回文字结果内容是markdown格式
     * @param response
     * @return
     * @throws IOException
     */
    public String getResByResp(Response response) throws IOException {
        assert response.body() != null;
        String bodyJsonStr = response.body().string();
        Map bodyMap = JSON.parseObject(bodyJsonStr, Map.class);
        JSONArray choices = (JSONArray) bodyMap.get("choices");
        JSONObject choiceJson = (JSONObject)choices.get(0);
        JSONObject message = (JSONObject)choiceJson.get("message");

        String resContext =  (String)message.get("content");
        return resContext;
    }

    @Override
    public String talkTextSingle(String reqContent) {
        // 此段可以存入MongoDB 或者 redis 、mysql中 根据userId 与 会话 id
        WxyyReqBody.Message mes = new WxyyReqBody.Message();
        mes.setRole("user");
        mes.setContent(reqContent);
        List<WxyyReqBody.Message> messages = new ArrayList<>();
        messages.add(mes);

        String reqBodyStr = getReqBodyStr(messages,new WxyyReqBody.WebSearch());
        Request request = bulidRequest(RequestBody.create(MediaType.parse("application/json"), reqBodyStr));

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            String resContext = getResByResp(response);
            return resContext;
        } catch (IOException e) {
            LOG.error("文心一言返回报错:"+e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
