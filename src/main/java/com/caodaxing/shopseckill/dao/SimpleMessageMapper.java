package com.caodaxing.shopseckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caodaxing.shopseckill.entity.SimpleMessage;
import com.caodaxing.shopseckill.entity.SimpleMessageExample;

public interface SimpleMessageMapper {
	
    long countByExample(SimpleMessageExample example);

    int deleteByExample(SimpleMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(SimpleMessage record);

    int insertSelective(SimpleMessage record);

    List<SimpleMessage> selectByExample(SimpleMessageExample example);

    SimpleMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SimpleMessage record, @Param("example") SimpleMessageExample example);

    int updateByExample(@Param("record") SimpleMessage record, @Param("example") SimpleMessageExample example);

    int updateByPrimaryKeySelective(SimpleMessage record);

    int updateByPrimaryKey(SimpleMessage record);
}