package com.caodaxing.shopseckill.service.oauth2;

import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthClientExample;

import java.util.List;

public interface OauthClientService {

    long countByExample(OauthClientExample example);

    int deleteByExample(OauthClientExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthClient record);

    int insertSelective(OauthClient record);

    List<OauthClient> selectByExample(OauthClientExample example);

    OauthClient selectByPrimaryKey(Long id);

    int updateByExampleSelective(OauthClient record, OauthClientExample example);

    int updateByExample(OauthClient record, OauthClientExample example);

    int updateByPrimaryKeySelective(OauthClient record);

    int updateByPrimaryKey(OauthClient record);

}
