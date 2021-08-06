package com.yuzh.webflux.Config;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NettyClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    @Value("${netty.port}")
    private Integer port;

    @Value("${netty.host}")
    private String host;

    @Autowired
    private NettyClientInitializer nettyClientInitializer;

    @Autowired
    private NettyClientHandler handler;

    private SocketChannel socketChannel;

    /**
     * 发送消息
     */
    public synchronized MessageProtocol sendMsg(MessageProtocol message) {
        log.info("向server发送的参数：{}", message.toString());
        handler.sendMessage(message);
        return handler.getResponse();
    }

//    @PostConstruct
    public void start() {
        log.info("=================================start=====================================");
        doConnect(new Bootstrap(), group);
    }

    public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(nettyClientInitializer);
        ChannelFuture future = bootstrap.connect();
        //客户端断线重连逻辑
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                log.info("连接TCP服务端成功");
            } else {
                log.error("连接失败，进行断线重连");
                future1.channel().eventLoop().schedule(() -> start(), 15, TimeUnit.SECONDS);
            }
        });
        socketChannel = (SocketChannel) future.channel();
    }
}
