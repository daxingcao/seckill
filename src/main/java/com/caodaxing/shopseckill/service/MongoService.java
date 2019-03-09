package com.caodaxing.shopseckill.service;

import com.caodaxing.shopseckill.entity.Shop;

/**
 * @author daxing.cao
 * @version 1.0
 *
 * @Description mongodb业务逻辑层接口
 */
public interface MongoService {

    /**
     * 保存商品信息到mongodb
     * @param shop 商品信息
     * @return 返回结果
     */
    int insert(Shop shop);

    /**
     * 更新monggodb中商品信息
     * @param shop
     */
    void update(Shop shop);

    /**
     * 获取shop信息
     * @param id ID
     * @return shop信息
     */
    Shop getShop(Long id);
}
