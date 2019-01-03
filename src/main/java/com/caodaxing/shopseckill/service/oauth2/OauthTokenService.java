package com.caodaxing.shopseckill.service.oauth2;

import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.entity.OauthTokenExample;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OauthTokenService {

    long countByExample(OauthTokenExample example);

    int insert(OauthToken record);

    int insertSelective(OauthToken record);

    List<OauthToken> selectByExample(OauthTokenExample example);

    OauthToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthToken record);

    int updateByPrimaryKey(OauthToken record);

    OauthToken queryOauthTokenByAccessToken(String accessToken);

    OAuthResponse generateAccessToken(Long oauthClientId) throws OAuthSystemException;

    OauthToken checkRefreshToken(Long clientId, String refreshToken);

    int updateAccessToken(Long id, String accessToken);

}
