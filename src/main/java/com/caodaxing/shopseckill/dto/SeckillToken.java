package com.caodaxing.shopseckill.dto;

/**
 * @description 封装秒杀地址的实体对象
 * @author Administrator
 *
 */
public class SeckillToken {

	//秒杀凭证
	private String token;
	//是否开启秒杀
	private boolean isOpen;
	//商品编号
	private String shopCode;
	//系统当前时间
	private long nowTime;
	//秒杀开始时间
	private long startTime;
	//秒杀结束时间
	private long endTime;
	
	public SeckillToken() {
		super();
	}
	
	public SeckillToken(boolean isOpen, String shopCode) {
		super();
		this.isOpen = isOpen;
		this.shopCode = shopCode;
	}

	public SeckillToken(boolean isOpen, String shopCode, String token) {
		this.isOpen = isOpen;
		this.shopCode = shopCode;
		this.token = token;
	}
	
	public SeckillToken(boolean isOpen, long nowTime, long startTime, long endTime) {
		this.isOpen = isOpen;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getNowTime() {
		return nowTime;
	}
	public void setNowTime(long nowTime) {
		this.nowTime = nowTime;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	
}
