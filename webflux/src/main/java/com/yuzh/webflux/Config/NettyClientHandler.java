package com.yuzh.webflux.Config;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    public static Map<String, Object> map = new HashMap<>();


    private ChannelHandlerContext ctx;
//    private ChannelPromise promise;
    private MessageProtocol response;
//
//    @Autowired
//    private NettyClient nettyClient;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            log.info(ctx.channel().remoteAddress() + "，超时类型：" + event.state());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 处理断开重连
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
//        int port = ipSocket.getPort();
//        String host = ipSocket.getHostString();
//        SwitchInfo switchInfo = new SwitchInfo();
//        switchInfo.setSwitchIp(host);
//        List<SwitchInfo> switchInfoList = switchDriverInfoService.getSwitchInfoList(switchInfo);
//        log.error("与设备"+host+":"+port+"连接断开!");
//        nettyClientMore.restart(switchInfoList.get(0));
    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
//        int port = ipSocket.getPort();
//        String host = ipSocket.getHostString();
//        log.info("与设备"+host+":"+port+"连接成功!");
//        ctx.fireChannelActive();
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(cause.getMessage());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol message) throws Exception {
        MessageProtocol msg = (MessageProtocol) message;
        if (msg != null) {
            response = message;
//            promise.setSuccess();
        }
//        else {
//            ctx.fireChannelRead(msg);
//        }
        ctx.writeAndFlush(msg);

    }

    public void sendMessage(MessageProtocol message){
        ctx.writeAndFlush(message);
    }

    public MessageProtocol getResponse() {
        return response;
    }
}