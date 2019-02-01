package com.caodaxing.shopseckill.exception.oauth2;

/**
 * @author daxing.cao
 * token验证错误异常类
 */
public class OAuth2TokenValidException extends OAuth2Exception {

    public OAuth2TokenValidException(String message) {
        super(message);
    }

    public OAuth2TokenValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
