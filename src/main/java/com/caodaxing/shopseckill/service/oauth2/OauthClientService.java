package com.caodaxing.shopseckill.service.oauth2;

import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthClientExample;
import java.util.List;
/**
 * @author daxing.cao
 */
public interface OauthClientService {

    long countByExample(OauthClientExample example);

    int insert(OauthClient record);

    int insertSelective(OauthClient record);

    List<OauthClient> selectByExample(OauthClientExample example);

    OauthClient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthClient record);

    int updateByPrimaryKey(OauthClient record);

    OauthClient checkClient(String clientId, String clientSecret);

    OauthClient isExistClient(String clientId);

    List<OauthClient> getListByParams(OauthClient oauthClient);

    int batchDeleteById(List isList);

}
