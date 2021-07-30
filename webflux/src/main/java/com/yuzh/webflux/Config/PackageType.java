package com.yuzh.webflux.Config;

import lombok.Data;

public enum PackageType {
    UNKONW((byte) 0X0),
    REQUEST((byte) 0X1),
    RESPONSE((byte) 0X2),
    INFORM((byte) 0X3),
    CONFORM((byte) 0X4),
    SERIANET((byte) 0X5),
    CONFIG((byte) 0X6);
    private byte type;

    PackageType(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
