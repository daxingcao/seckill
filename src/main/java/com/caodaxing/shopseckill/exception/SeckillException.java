package com.caodaxing.shopseckill.exception;

/**
 * 秒杀异常类
 * @author daxing.cao
 *
 */
public class SeckillException extends RuntimeException {

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
