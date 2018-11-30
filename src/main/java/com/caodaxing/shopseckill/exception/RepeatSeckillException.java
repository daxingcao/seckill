package com.caodaxing.shopseckill.exception;

/**
 * 重复秒杀异常类
 * @author Administrator
 *
 */
public class RepeatSeckillException extends SeckillException {

	public RepeatSeckillException(String message) {
		super(message);
	}

	public RepeatSeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
