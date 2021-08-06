package com.yuzh.huayun.nettyConfig;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
@ChannelHandler.Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("断开连接执行");
    }
 
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接成功执行");
    }
 
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("收到消息执行：" + msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (StringUtils.hasLength((String) msg)) {
//            Attribute<String> attr =ctx.channel().attr(AttributeMapConstant.msgkey);
//            synchronized(attr){
//                attr.set(msg.toString());
//                attr.notify();
//                log.info("此时收到的响应消息是："+msg.toString());
//            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("抛出异常执行");
    }
}