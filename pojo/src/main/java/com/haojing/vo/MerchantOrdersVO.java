package com.haojing.vo;

import io.swagger.models.auth.In;

/**
 * 商户订单对象
 */
public class MerchantOrdersVO {

    /**
     * 商家订单id
     */
    private String merchantOrderId;
    /**
     * 商家id
     */
    private String merchantUserId;
    /**
     * 实际支付金额
     */
    private Integer amount;
    /**
     * 支付方式
     */
    private Integer payMethod;
    /**
     * 支付成功后的回调地址
     */
    private String returnUrl;

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(String merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
