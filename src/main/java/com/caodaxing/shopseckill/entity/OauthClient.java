package com.caodaxing.shopseckill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author daxing.cao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthClient {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 应用ID
     */
    private String clientId;

    /**
     * 应用安全key
     */
    private String clientSecret;

    /**
     * 应用名称
     */
    private String clientName;

    /**
     * 应用code
     */
    private String code;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime expiryDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
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