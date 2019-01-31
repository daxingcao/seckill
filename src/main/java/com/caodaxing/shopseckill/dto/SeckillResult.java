package com.caodaxing.shopseckill.dto;

import com.caodaxing.shopseckill.common.enums.SeckillStatus;
import com.caodaxing.shopseckill.entity.ShopOrder;

/**
 * 该对象为执行秒杀后返回的结果对象,里面保存了用户秒杀商品的状态, 以及{@link ShopOrder} 信息
 * 
 * @version 1.0.0
 * @time 2018-11-14
 * @author daxing.cao
 *
 */
public class SeckillResult {

	//商品编号
	private String shopCode;
	//购买状态
	private SeckillStatus status;
	//商品订单
	private ShopOrder shopOrder;

	public SeckillResult() {
		super();
	}

	public SeckillResult(String shopCode, SeckillStatus status) {
		super();
		this.shopCode = shopCode;
		this.status = status;
	}

	public SeckillResult(String shopCode, SeckillStatus status, ShopOrder shopOrder) {
		super();
		this.shopCode = shopCode;
		this.status = status;
		this.shopOrder = shopOrder;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public SeckillStatus getStatus() {
		return status;
	}

	public void setStatus(SeckillStatus status) {
		this.status = status;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

}
