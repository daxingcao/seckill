package com.caodaxing.shopseckill.dto;

/**
 * @author daxing.cao
 * @description 统一返回页面包装数据类
 */
public class Returns<T> {

	private boolean success;
	private T data;
	private String msg;

	public Returns(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public Returns(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Returns(boolean success, T data, String msg) {
		this.success = success;
		this.data = data;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
