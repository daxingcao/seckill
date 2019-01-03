package com.caodaxing.shopseckill.dao;

import java.util.List;
import com.caodaxing.shopseckill.entity.SimpleMessage;
import com.caodaxing.shopseckill.entity.SimpleMessageExample;

public interface SimpleMessageMapper {
	
    long countByExample(SimpleMessageExample example);

    int insert(SimpleMessage record);

    int insertSelective(SimpleMessage record);

    List<SimpleMessage> selectByExample(SimpleMessageExample example);

    SimpleMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SimpleMessage record);

    int updateByPrimaryKey(SimpleMessage record);
}