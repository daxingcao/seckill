package com.caodaxing.shopseckill.exception;

/**
 * 秒杀结束异常类
 * @author daxing.cao
 *
 */
public class CloseSeckillException extends SeckillException {

	public CloseSeckillException(String message) {
		super(message);
	}

	public CloseSeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
