package com.caodaxing.shopseckill.common.enums;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public enum OAuth2Status {

    SUCCESS(200,true, "验证通过!","authentication OK"),
    CLIENT_ID_NULL(100,false,"client_id为空!","client_id is null!"),
    CLIENT_NOT_EXIST(101,false,"应用不存在!","client is not exist!"),
    REFRESH_TOKEN_ERROR(102,false,"refresh_token错误!","refresh_token is error!"),
    REFRESH_TOKEN_EXPIRY(103,false,"refresh_token已过期!","refresh_token is expiry!"),
    REFRESH_TOKEN_NULL(104,false,"refresh_token为空!","refresh_token is null!"),
    TOKEN_ERROR(104,false,"access_token错误!","access_token is error!"),
    TOKEN_EXPIRY(105,false,"access_token已过期!","access_token was expiry!"),
    TOKEN_ISNULL(106,false,"access_token为空!","access_token is null!"),
    SYSTEM_ERROR(400,false,"系统错误!","system error!");

    private static String LANGUAGE;

    static{
        InputStream is = OAuth2Status.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            LANGUAGE = properties.getProperty("oauth.message.language","CN");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer statusCode;
    private Boolean success;
    private String cnMsg;
    private String enMsg;

    OAuth2Status(Integer statusCode,boolean success, String cnMsg,String enMsg) {
        this.statusCode = statusCode;
        this.success = success;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg(){
        if(StringUtils.equals(LANGUAGE,"CN")){
            return this.cnMsg;
        }else if(StringUtils.equals(LANGUAGE,"EN")){
            return this.enMsg;
        }else {
            return this.cnMsg;
        }
    }

    public String getCnMsg() {
        return this.cnMsg;
    }

    public String getEnMsg(){
        return this.enMsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public OAuth2Status valueOf(Integer status) {
        OAuth2Status[] msgStatuses = OAuth2Status.values();
        for (OAuth2Status msgStatus : msgStatuses) {
            if(status == msgStatus.getStatusCode()){
                return msgStatus;
            }
        }
        return null;
    }

    public Map<String, Object> toReturns(){
        Map<String, Object> map = Maps.newHashMap();
        map.put("code",this.statusCode);
        map.put("msg",this.getMsg());
        map.put("success",this.success);
        return map;
    }

}
