package com.caodaxing.shopseckill.exception.oauth2;

public class OAuth2Exception extends RuntimeException {

    public OAuth2Exception() {
    }

    public OAuth2Exception(String message) {
        super(message);
    }

    public OAuth2Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
