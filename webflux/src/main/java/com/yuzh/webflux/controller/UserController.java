package com.yuzh.webflux.controller;

import com.yuzh.webflux.Config.*;
import com.yuzh.webflux.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private NettyClient nettyClient;

    @Autowired
    private MessageProtocol message;

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        byte[] bytes = loginRequest.toByteArray();
        message.setByMsgType(PackageType.REQUEST.getType())
                .setUiSourceID(RequestId.HOST.getUiSourceID())
                .setUiSessionID(0)
                .setUiFuncNo(FuncNo.LOGIN.getCode())
                .setUiMsgLen(77)
                .setUiRetCode(0)
                .setUiMktCode((byte) 0)
                .setByReserved("")
                .setContent(bytes);
        nettyClient.sendMsg(message);
        return null;
    }
}
