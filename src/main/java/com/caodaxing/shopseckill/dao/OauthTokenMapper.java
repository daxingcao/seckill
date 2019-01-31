package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.entity.OauthTokenExample;
import java.util.List;

/**
 * @author daxing.cao
 */
public interface OauthTokenMapper {
    long countByExample(OauthTokenExample example);

    int insert(OauthToken record);

    int insertSelective(OauthToken record);

    List<OauthToken> selectByExample(OauthTokenExample example);

    OauthToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthToken record);

    int updateByPrimaryKey(OauthToken record);
}