package com.yuzh.webflux;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TcpDecoderHanlder extends MessageToMessageDecoder  {
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpDecoderHanlder.class);
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list){
        LOGGER.info("解析client上报数据",o);
    }
}