package com.yuzh.huayun.nettyConfig;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageDecoder extends ReplayingDecoder {
    private static final int headLength = 32;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //需要将二朝向字节码->MessageProtocol封装成messageProtocol对象
        MessageProtocol messageProtocol = new MessageProtocol();
        int szMagicNum = in.readUnsignedShort();
        if (szMagicNum != 0xdada) {
            return;
        }
        messageProtocol.setByVersion(in.readByte());
        messageProtocol.setByMsgType(in.readByte());
        messageProtocol.setUiSourceID(in.readShort());
        messageProtocol.setUiSessionID(in.readInt());
        messageProtocol.setUiFuncNo(in.readShort());
        int len = in.readInt();
        messageProtocol.setUiMsgLen(len);
        messageProtocol.setUiMsgSeq(in.readInt());
        messageProtocol.setUiRetCode(in.readInt());
        messageProtocol.setUiMktCode(in.readByte());
        messageProtocol.setByReserved(in.readCharSequence(7, CharsetUtil.UTF_8).toString());
        byte[] content = new byte[len - headLength];
        in.readBytes(content);
        messageProtocol.setContent(content);
        out.add(messageProtocol);
    }

}
