package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.ShopOrder;
import com.caodaxing.shopseckill.entity.ShopOrderExample;
import com.caodaxing.shopseckill.entity.ShopOrderKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopOrderMapper {
    long countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    int deleteByPrimaryKey(ShopOrderKey key);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(ShopOrderKey key);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
    
    ShopOrder queryShopOrder(@Param("shopCode")String shopCode, @Param("userId")Long userId);
}