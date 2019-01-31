package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthClientExample;
import java.util.List;

/**
 * @author daxing.cao
 */
public interface OauthClientMapper {
    long countByExample(OauthClientExample example);

    int insert(OauthClient record);

    int insertSelective(OauthClient record);

    List<OauthClient> selectByExample(OauthClientExample example);

    OauthClient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthClient record);

    int updateByPrimaryKey(OauthClient record);
}