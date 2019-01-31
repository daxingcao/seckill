package com.caodaxing.shopseckill.entity;
/**
 * @author daxing.cao
 */
public class ShopOrderKey {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品编号
     */
    private String shopCode;

    public ShopOrderKey() {
		super();
	}

	public ShopOrderKey(Long userId, String shopCode) {
		super();
		this.userId = userId;
		this.shopCode = shopCode;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }
}