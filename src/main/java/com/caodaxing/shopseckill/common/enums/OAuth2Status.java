package com.caodaxing.shopseckill.common.enums;

import com.google.common.collect.Maps;

import java.util.Map;

public enum OAuth2Status {

    SUCCESS(200,true, "authentication OK"),
    CLIENT_ID_NULL(100,false,"client_id is null!"),
    CLIENT_SECRET_NULL(101,false,"client_secret is null!"),
    CODE_ERROR(102,false,"authorization_code is error!"),
    CODE_EXPIRY(103,false,"authorization_code is expiry!"),
    TOKEN_ERROR(104,false,"access_token is error!"),
    TOKEN_EXPIRY(105,false,"access_token was expiry!"),
    TOKEN_ISNULL(106,false,"access_token is null!"),
    SYSTEM_ERROR(400,false,"system error!");

    private Integer status;
    private Boolean success;
    private String msg;

    OAuth2Status(Integer status,boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
            if(status == msgStatus.getStatus()){
                return msgStatus;
            }
        }
        return null;
    }

    public Map<String, Object> toReturns(){
        Map<String, Object> map = Maps.newHashMap();
        map.put("code",this.status);
        map.put("message",this.msg);
        map.put("success",this.success);
        return map;
    }

}
