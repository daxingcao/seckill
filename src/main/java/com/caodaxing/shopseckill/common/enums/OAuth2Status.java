package com.caodaxing.shopseckill.common.enums;

import com.alibaba.druid.util.StringUtils;
import com.caodaxing.shopseckill.autoconfigure.SystemProperties;
import com.caodaxing.shopseckill.utils.WebApplicationUtils;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author daxing.cao
 */
public enum OAuth2Status {

    /**
     * 验证通过
     */
    SUCCESS(200,true, "验证通过!","authentication OK"),
    /**
     * client_id为空
     */
    CLIENT_ID_NULL(100,false,"client_id为空!","client_id is null!"),
    /**
     * 应用不存在
     */
    CLIENT_NOT_EXIST(101,false,"应用不存在!","client is not exist!"),
    /**
     * refresh_token错误
     */
    REFRESH_TOKEN_ERROR(102,false,"refresh_token错误!","refresh_token is error!"),
    /**
     * refresh_token已过期
     */
    REFRESH_TOKEN_EXPIRY(103,false,"refresh_token已过期!","refresh_token is expiry!"),
    /**
     * refresh_token为空
     */
    REFRESH_TOKEN_NULL(104,false,"refresh_token为空!","refresh_token is null!"),
    /**
     * access_token错误
     */
    TOKEN_ERROR(104,false,"access_token错误!","access_token is error!"),
    /**
     * access_token已过期
     */
    TOKEN_EXPIRY(105,false,"access_token已过期!","access_token was expiry!"),
    /**
     * access_token为空
     */
    TOKEN_ISNULL(106,false,"access_token为空!","access_token is null!"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(400,false,"系统错误!","system error!");

    private static SystemProperties properties;

    static{
        properties = WebApplicationUtils.getBean("systemProperties", SystemProperties.class);
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
        String language = properties.getAuth().getMessageLanguage().toString();
        if(StringUtils.equals(language,"CN")){
            return this.cnMsg;
        }else if(StringUtils.equals(language,"EN")){
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
            if(status.equals(msgStatus.getStatusCode())){
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
