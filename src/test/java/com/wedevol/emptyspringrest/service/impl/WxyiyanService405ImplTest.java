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
        // 如何精确输出问题答案 ？
        // 1、入参限制 精确范围 temperature 设置尽量低  就是回答的问题更加精准
        // 2、入参设置 文本输出内容多样性低 top_p 0 为了确保不会乱输出格式 尽量简洁 将返回的markDown格式内容直接输出无需带markDown输出
        // 3、问题精准输出也取决输入问题的规范性，严格性。输出的问题结果自然也就精准简洁。例如：仅输出｛content｝,无需输出其他内容

        // 可以将提问内容 默认加上这一段话 仅输出｛content｝,无需输出其他内容
        // 仅罗列10种常用药的通用名，无需输出其他内容 仅输出盘尼西尼的作用，无需输出其他内容
        String res = wxyiyanService.talkTextSingle("罗列10种常用药的通用名");
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
        WxyyReqBody wxyyReqBody = new WxyyReqBody(model,messages,new WxyyReqBody.ResponseFormat("json_object"),0.1d,0d,web_search);
        String wxyyReqBodyStr = JSON.toJSONString(wxyyReqBody);
        return wxyyReqBodyStr;
    }
}