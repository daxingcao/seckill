package com.caodaxing.shopseckill.entity;

import java.util.Date;

public class SimpleMessage {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 发送者id，对应用户表主键id
     */
    private String sender;

    /**
     * 接受者id，对应用户表主键id
     */
    private String receiver;

    /**
     * 消息发送时间
     */
    private Date createDt;

    /**
     * 更新时间
     */
    private Date updateDt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}