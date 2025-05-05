package com.wedevol.emptyspringrest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wedevol.emptyspringrest.config.OkHttpConfig;
import com.wedevol.emptyspringrest.entity.WxyyReqBody;
import com.wedevol.emptyspringrest.service.WxyiyanService;
import com.wedevol.emptyspringrest.utils.WxyiyanUtil;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxyiyanService405Impl implements WxyiyanService {

    private static final Logger LOG = LoggerFactory.getLogger(WxyiyanService405Impl.class);

    @Autowired
    private OkHttpClient okHttpClient;

    @Value(value = "${wenxin.api-key}")
    private String apiKey;

    @Value(value = "${wenxin.url}")
    private String url;

    @Value(value = "${wenxin.model}")
    private String model;

    @Override
    public String talk(String reqContext) throws Exception{
        // 此段可以存入MongoDB 或者 redis 、mysql中 根据userId 与 会话 id
        WxyyReqBody.Message mes = new WxyyReqBody.Message();
        mes.setRole("user");
        mes.setContent(reqContext);
        List<WxyyReqBody.Message> messages = new ArrayList<>();
        messages.add(mes);

        String reqBodyStr = WxyiyanUtil.getReqBodyStr(messages,new WxyyReqBody.WebSearch());
        Request request = WxyiyanUtil.bulidRequest(RequestBody.create(MediaType.parse("application/json"), reqBodyStr));

        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()){
            String resContext = WxyiyanUtil.getResultByResp(response);
            return resContext;
        }


        return "文心一言出错,没有返回消息";
    }



}
