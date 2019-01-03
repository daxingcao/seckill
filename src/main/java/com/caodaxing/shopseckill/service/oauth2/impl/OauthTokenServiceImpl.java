package com.caodaxing.shopseckill.service.oauth2.impl;

import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.dao.OauthTokenMapper;
import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.entity.OauthTokenExample;
import com.caodaxing.shopseckill.service.oauth2.OauthTokenService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OauthTokenServiceImpl implements OauthTokenService {

    @Autowired
    private OauthTokenMapper oauthTokenMapper;

    @Value("${refresh.token.expiry.date}")
    private Long REFRESH_TOKEN_EXPIRY_DATE;
    @Value("${access.token.expiry.date}")
    private Long ACCESS_TOKEN_EXPIRY_DATE;

    @Override
    public long countByExample(OauthTokenExample example) {
        return oauthTokenMapper.countByExample(example);
    }

    @Override
    public int insert(OauthToken record) {
        return oauthTokenMapper.insert(record);
    }

    @Override
    public int insertSelective(OauthToken record) {
        return oauthTokenMapper.insertSelective(record);
    }

    @Override
    public List<OauthToken> selectByExample(OauthTokenExample example) {
        return oauthTokenMapper.selectByExample(example);
    }

    @Override
    public OauthToken selectByPrimaryKey(Long id) {
        return oauthTokenMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OauthToken record) {
        return oauthTokenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OauthToken record) {
        return oauthTokenMapper.updateByPrimaryKey(record);
    }

    @Override
    public OauthToken queryOauthTokenByAccessToken(String accessToken) {
        OauthTokenExample example = new OauthTokenExample();
        example.createCriteria().andAccessTokenEqualTo(accessToken).andIsDeleteEqualTo(SystemFiled.DeleteStatus.NOT_DELETED.getCode());
        List<OauthToken> lists = selectByExample(example);
        if (lists != null && !lists.isEmpty()) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public OAuthResponse generateAccessToken(Long oauthClientId) throws OAuthSystemException {
        try {
            //生成access_token
            OAuthIssuer oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
            String accessToken = oAuthIssuer.accessToken();
            String refreshToken = oAuthIssuer.refreshToken();
            OauthToken oauthToken = OauthToken.builder().accessToken(accessToken).refreshToken(refreshToken).oauthClientId(oauthClientId)
                    .createDate(LocalDateTime.now()).expiryDate(LocalDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRY_DATE))
                    .refreshExpiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRY_DATE)).build();
            this.insertSelective(oauthToken);
            return OAuthASResponse.tokenResponse(200).setAccessToken(accessToken).setRefreshToken(refreshToken).buildJSONMessage();
        } catch (Exception e) {
            throw new OAuthSystemException(e);
        }
    }

    @Override
    public OauthToken checkRefreshToken(Long clientId, String refreshToken) {
        OauthTokenExample example = new OauthTokenExample();
        example.createCriteria().andOauthClientIdEqualTo(clientId).andRefreshTokenEqualTo(refreshToken).andIsDeleteEqualTo(SystemFiled.DeleteStatus.NOT_DELETED.getCode());
        List<OauthToken> lists = this.selectByExample(example);
        if (lists != null && !lists.isEmpty()) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    public int updateAccessToken(Long id, String accessToken) {
        OauthToken oauthToken = OauthToken.builder().id(id).accessToken(accessToken).expiryDate(LocalDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRY_DATE)).build();
        return updateByPrimaryKeySelective(oauthToken);
    }

}
