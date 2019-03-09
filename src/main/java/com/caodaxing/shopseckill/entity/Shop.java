package com.caodaxing.shopseckill.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体对象信息
 * @version 1.0.0
 */
@Data
@Builder
@Accessors
public class Shop {
    /**
     * 商品主键id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String shopCode;

    /**
     * 商品名称
     */
    private String shopName;

    /**
     * 商品价格
     */
    private BigDecimal shopPrice;

    /**
     * 商品相关介绍
     */
    private String shopIntroduce;

    /**
     * 是否删除:0.未删除;1.已删除
     */
    private Byte isDelete;

    /**
     * 是否参与秒杀:0.不参与;1.参与
     */
    private Byte isSeckill;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品库存
     */
    private Long shopNumber;

    /**
     * 备注
     */
    private String remark;

}