package com.haojing.enums;

public enum OrderStatusEnum {
    WAIT_PAY(10,"待付款"),
    WAIT_DELUVER(20,"已付款，待发货"),
    WAIT_RECEIVE(30,"已付款，待收货"),
    SUCCESS(40,"交易成功"),
    CLOSE(50,"交易关闭");
    public final Integer type;
    public final String value;

    OrderStatusEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
