package com.caodaxing.shopseckill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthToken {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 对应应用登录信息表主键ID
     */
    private Long oauthClientId;

    /**
     * 应用安全key
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private LocalDateTime expiryDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 是否删除;0.未删除,1.已删除
     */
    private Integer isDelete;

}