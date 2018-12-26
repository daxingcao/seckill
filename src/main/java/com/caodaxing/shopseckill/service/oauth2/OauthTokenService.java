package com.caodaxing.shopseckill.service.oauth2;

import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.entity.OauthTokenExample;

import java.util.List;

public interface OauthTokenService {

    long countByExample(OauthTokenExample example);

    int deleteByExample(OauthTokenExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthToken record);

    int insertSelective(OauthToken record);

    List<OauthToken> selectByExample(OauthTokenExample example);

    OauthToken selectByPrimaryKey(Long id);

    int updateByExampleSelective(OauthToken record, OauthTokenExample example);

    int updateByExample(OauthToken record, OauthTokenExample example);

    int updateByPrimaryKeySelective(OauthToken record);

    int updateByPrimaryKey(OauthToken record);

    OauthToken queryOauthTokenBy(String accessToken);

}
