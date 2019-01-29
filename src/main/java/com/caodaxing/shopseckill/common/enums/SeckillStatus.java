package com.caodaxing.shopseckill.common.enums;

public enum SeckillStatus {

	/**
	 * 秒杀成功
	 */
	SUCCESS(1,"秒杀成功!"),
	/**
	 * 重复秒杀
	 */
	REPEAT(2,"重复秒杀!"),
	/**
	 * 秒杀结束
	 */
	END(3,"秒杀结束!"),
	/**
	 * 系统异常
	 */
	INNER_ERROR(4,"系统异常!"),
	/**
	 * 商品token错误
	 */
	TOKEN_ERROR(5,"商品token错误!");

	private int status;
	
	private String statusInfo;

	SeckillStatus(int status, String statusInfo) {
		this.status = status;
		this.statusInfo = statusInfo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
	
}
