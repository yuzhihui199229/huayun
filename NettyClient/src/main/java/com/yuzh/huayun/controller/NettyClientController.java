package com.yuzh.huayun.controller;

import com.yuzh.huayun.config.NettyClientBootStrap;
import io.netty.util.AttributeKey;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class NettyClientController {
 
    /**
     * @description: 模拟向服务器发送消息
     * @param
     * @return: java.lang.String
     */
    @RequestMapping("/req")
    public String req() {
        AttributeKey<String> attr = AttributeKey.valueOf("MessageFrame.msg");
        String msg = "{\"msgType\":\"req\",\"clientId\":\"请求数据\"}";
        try {
            return NettyClientBootStrap.sendMessage(msg,attr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "fail";
    }
 
}