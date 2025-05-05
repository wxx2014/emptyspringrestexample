package com.wedevol.emptyspringrest.service;

import com.wedevol.emptyspringrest.entity.WxyyReqBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public interface WxyiyanService {

//    /**
//     * 对话
//     * @param context
//     * @return
//     */
//    String talk(String context) throws Exception;


    /**
     * 获取请求内容字符串
     * @param messages
     * @param web_search
     * @return
     */
    String getReqBodyStr(List<WxyyReqBody.Message> messages, WxyyReqBody.WebSearch web_search);

    /**
     * 构建文心一言请求对象
     * @param body
     * @return
     */
    Request bulidRequest(RequestBody body);

    /**
     * 返回文字结果内容是markdown格式
     */
    String getResByResp(Response response) throws IOException;


    /**
     * 简单的文心一言文本对话
     * @param reqContent
     * @return
     */
    String talkTextSingle(String reqContent);
}
