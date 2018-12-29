package com.caodaxing.shopseckill.exception.oauth2;

/**
 * token验证过时异常类
 */
public class OAuth2TokenExpiryException extends OAuth2Exception {

    public OAuth2TokenExpiryException(String message) {
        super(message);
    }

    public OAuth2TokenExpiryException(String message, Throwable cause) {
        super(message, cause);
    }
}
