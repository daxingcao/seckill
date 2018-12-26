package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.entity.OauthTokenExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OauthTokenMapper {
    long countByExample(OauthTokenExample example);

    int deleteByExample(OauthTokenExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthToken record);

    int insertSelective(OauthToken record);

    List<OauthToken> selectByExample(OauthTokenExample example);

    OauthToken selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthToken record, @Param("example") OauthTokenExample example);

    int updateByExample(@Param("record") OauthToken record, @Param("example") OauthTokenExample example);

    int updateByPrimaryKeySelective(OauthToken record);

    int updateByPrimaryKey(OauthToken record);
}