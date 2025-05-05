package com.wedevol.emptyspringrest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wedevol.emptyspringrest.EntryPoint;
import com.wedevol.emptyspringrest.entity.WxyyReqBody;
import com.wedevol.emptyspringrest.service.WxyiyanService;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = EntryPoint.class)
@RunWith(SpringRunner.class)
public class WxyiyanService405ImplTest {

    @Autowired
    private WxyiyanService wxyiyanService;

    @Value(value = "${wenxin.model}")
    private String model;

    @Test
    public void talk() throws Exception {
        String res = wxyiyanService.talk("现在中国旅游出行最好时机是什么时候才能舒适");
        System.out.println(res);
    }

    @Test
    public void test1(){
        String jsonStr = "{\"model\":\"ernie-4.5-turbo-vl-32k\",\"messages\":[{\"role\":\"user\",\"content\":\"索马里海盗\"}],\"web_search\":{\"enable\":false,\"enable_citation\":false,\"enable_trace\":false}}";
        WxyyReqBody wxyyReqBody = JSONObject.parseObject(jsonStr, WxyyReqBody.class);
        System.out.println(wxyyReqBody);

        String jsonStr2 = getReqBodyStr("索马里海盗");
        WxyyReqBody wxyyReqBody2 = JSONObject.parseObject(jsonStr2, WxyyReqBody.class);

        boolean equals = wxyyReqBody.equals(wxyyReqBody2);
        System.out.println(equals);

    }

    @Test
    public void test2(){
        String jsonStr = getReqBodyStr("索马里海盗");
        System.out.println(jsonStr);
    }


    private @NotNull String getReqBodyStr(String reqContext) {
        WxyyReqBody.Message message = new WxyyReqBody.Message();
        message.setRole("user");
        message.setContent(reqContext);
        List<WxyyReqBody.Message> messages  = new ArrayList<>();
        messages.add(message);
        WxyyReqBody.WebSearch web_search = new WxyyReqBody.WebSearch();
        WxyyReqBody wxyyReqBody = new WxyyReqBody(model,messages,web_search);
        String wxyyReqBodyStr = JSON.toJSONString(wxyyReqBody);
        return wxyyReqBodyStr;
    }
}