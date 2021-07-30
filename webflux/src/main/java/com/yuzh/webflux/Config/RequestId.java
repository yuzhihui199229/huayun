package com.yuzh.webflux.Config;

public enum RequestId {
    HOST((short)0X1000),FPGA((short)0X1001);
    private short uiSourceID;

    RequestId(short uiSourceID) {
        this.uiSourceID = uiSourceID;
    }

    public short getUiSourceID() {
        return uiSourceID;
    }

    public void setUiSourceID(short uiSourceID) {
        this.uiSourceID = uiSourceID;
    }
}
