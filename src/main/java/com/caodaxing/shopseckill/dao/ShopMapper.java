package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.Shop;
import com.caodaxing.shopseckill.entity.ShopExample;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author daxing.cao
 */
public interface ShopMapper {
	
    long countByExample(ShopExample example);

    int insert(Shop record);

    int insertSelective(Shop record);

    List<Shop> selectByExample(ShopExample example);

    Shop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    
    /**********************************************/
    Shop queryShop(String shopCode);
    
    List<Shop> shopList(Integer isSeckill);
    
    int seckillExecution(@Param("shopCode")String shopCode, @Param("nowTime")Date nowTime);
}