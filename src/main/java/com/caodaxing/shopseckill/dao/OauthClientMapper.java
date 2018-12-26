package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthClientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OauthClientMapper {
    long countByExample(OauthClientExample example);

    int deleteByExample(OauthClientExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthClient record);

    int insertSelective(OauthClient record);

    List<OauthClient> selectByExample(OauthClientExample example);

    OauthClient selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthClient record, @Param("example") OauthClientExample example);

    int updateByExample(@Param("record") OauthClient record, @Param("example") OauthClientExample example);

    int updateByPrimaryKeySelective(OauthClient record);

    int updateByPrimaryKey(OauthClient record);
}