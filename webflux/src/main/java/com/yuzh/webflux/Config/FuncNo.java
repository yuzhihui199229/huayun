package com.yuzh.webflux.Config;

public enum FuncNo {
    LOGIN((short)0X7000);
    private short code;

    FuncNo(short code) {
        this.code = code;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }
}
