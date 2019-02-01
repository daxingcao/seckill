package com.caodaxing.shopseckill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author daxing.cao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    /**
     * 用户登录表ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 上次登录时间
     */
    private Date lastDate;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 登录状态, 0.未登录, 1.已登录
     */
    private Integer loginStatus;

    /**
     * 对应数据信息表id
     */
    private Integer userId;

    /**
     * 头像文件id,对应sysfile表id
     */
    private Integer headFileId;

}