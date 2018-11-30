package com.caodaxing.shopseckill.entity;

import java.util.Date;

/**
 * 商品订单类
 * @author Administrator
 * @version 1.0.0
 */
public class ShopOrder extends ShopOrderKey {
    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除:0.未删除;1.已删除
     */
    private Byte isDelete;
    
    

    public ShopOrder() {
		super();
	}

	public ShopOrder(String orderCode, Date createTime, Byte isDelete) {
		super();
		this.orderCode = orderCode;
		this.createTime = createTime;
		this.isDelete = isDelete;
	}

	public ShopOrder(Long userId, String shopCode,String orderCode) {
		super(userId, shopCode);
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}