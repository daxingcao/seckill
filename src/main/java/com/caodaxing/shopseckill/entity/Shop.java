package com.caodaxing.shopseckill.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体对象信息
 * @version 1.0.0
 */
public class Shop {
    /**
     * 商品主键id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String shopCode;

    /**
     * 商品名称
     */
    private String shopName;

    /**
     * 商品价格
     */
    private BigDecimal shopPrice;

    /**
     * 商品相关介绍
     */
    private String shopIntroduce;

    /**
     * 是否删除:0.未删除;1.已删除
     */
    private Byte isDelete;

    /**
     * 是否参与秒杀:0.不参与;1.参与
     */
    private Byte isSeckill;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品库存
     */
    private Long shopNumber;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getShopIntroduce() {
        return shopIntroduce;
    }

    public void setShopIntroduce(String shopIntroduce) {
        this.shopIntroduce = shopIntroduce;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsSeckill() {
        return isSeckill;
    }

    public void setIsSeckill(Byte isSeckill) {
        this.isSeckill = isSeckill;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(Long shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}