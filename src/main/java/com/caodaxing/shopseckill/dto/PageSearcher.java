package com.caodaxing.shopseckill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSearcher<T> {

    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNumber;
    /**
     * 排序方式: asc顺序;desc倒序
     */
    private String order;
    /**
     * 排序字段名
     */
    private String sort;
    /**
     * 查询的对象数据
     */
    private T data;

}
