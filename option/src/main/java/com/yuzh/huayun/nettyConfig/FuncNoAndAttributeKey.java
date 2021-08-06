package com.yuzh.huayun.nettyConfig;

public enum FuncNoAndAttributeKey {
    LOGIN((short)0X7000,"7000");
    private short funcNo;
    private String attributeKey;

    FuncNoAndAttributeKey(short funcNo, String attributeKey) {
        this.funcNo = funcNo;
        this.attributeKey = attributeKey;
    }

    public short getFuncNo() {
        return funcNo;
    }

    public void setFuncNo(short funcNo) {
        this.funcNo = funcNo;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }
}
