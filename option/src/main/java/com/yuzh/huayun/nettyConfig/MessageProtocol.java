package com.yuzh.huayun.nettyConfig;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@ToString
@Component
@Accessors(chain = true)
public class MessageProtocol implements Serializable {
    private short szMagicNum=(short) 0XDADA;
    private byte byVersion=0X01;
    private byte byMsgType;
    private short uiSourceID;
    private int uiSessionID;
    private short uiFuncNo;
    private int uiMsgLen;
    private int uiMsgSeq;
    private int uiRetCode;
    private byte uiMktCode;
    private String byReserved;
    private byte[] content;
}
