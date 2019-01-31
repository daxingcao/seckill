package com.caodaxing.shopseckill.exception.oauth2;
/**
 * @author daxing.cao
 */
public class OAuth2TokenNullException extends OAuth2Exception {

    public OAuth2TokenNullException(String message) {
        super(message);
    }

    public OAuth2TokenNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
