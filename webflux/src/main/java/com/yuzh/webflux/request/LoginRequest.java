package com.yuzh.webflux.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest implements Serializable {

    private String userID;//Char[12]
    private String password;//Char[32]
    private Integer clientType;//char

    public byte[] toByteArray(){
        ByteBuf buf = Unpooled.buffer(45);
        buf.writeCharSequence(userID, StandardCharsets.UTF_8);
        buf.writeZero(12-userID.getBytes(StandardCharsets.UTF_8).length);
        buf.writeCharSequence(password, StandardCharsets.UTF_8);
        buf.writeZero(32-password.getBytes(StandardCharsets.UTF_8).length);
        buf.writeByte(clientType);
        return buf.array();
    }
}
