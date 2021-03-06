package com.haojing.vo;

/**
 *商品对象
 */
public class ShopcartVO {
    /**
     * 商品Id
     */
    private String itemId;
    /**
     * 商品图片
     */
    private String itemImgUrl;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 规格id
     */
    private String specId;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * 折扣价格
     */
    private String priceDiscount;
    /**
     * 市场价格
     */
    private String priceNormal;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(String priceNormal) {
        this.priceNormal = priceNormal;
    }

}
