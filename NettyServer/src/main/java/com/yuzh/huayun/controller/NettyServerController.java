package com.yuzh.huayun.controller;

import com.yuzh.huayun.config.NettyServerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/server")
public class NettyServerController {

    @GetMapping("/clientList")
    public Map<String, NettyServerHandler.NettyClient> clientList() {
        return NettyServerHandler.NettyChannelMap.map;
    }

}