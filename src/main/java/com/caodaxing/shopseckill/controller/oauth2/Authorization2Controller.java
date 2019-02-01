package com.caodaxing.shopseckill.controller.oauth2;

import com.alibaba.druid.util.StringUtils;
import com.caodaxing.shopseckill.common.enums.OAuth2Status;
import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.service.oauth2.OauthClientService;
import com.caodaxing.shopseckill.service.oauth2.OauthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * @author daxing.cao
 * @description
 */
@Slf4j
@Controller
@RequestMapping("/oauth")
public class Authorization2Controller {

    @Autowired
    private OauthClientService oauthClientService;
    @Autowired
    private OauthTokenService oauthTokenService;

    @RequestMapping("/authorization")
    public void authorization(HttpServletRequest request, HttpServletResponse response) throws IOException, OAuthSystemException {
        OAuthResponse oAuthResponse;
        try {
            OAuthRequest oauthRequest = new OAuthTokenRequest(request);
            String clientId = oauthRequest.getClientId();
            String clientSecret = oauthRequest.getClientSecret();
            OauthClient oauthClient = oauthClientService.checkClient(clientId, clientSecret);
            if (StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret)
                    || oauthClient == null) {
                throw OAuthProblemException.error(OAuth2Status.CLIENT_NOT_EXIST.getMsg());
            }
            oAuthResponse = oauthTokenService.generateAccessToken(oauthClient.getId());
        } catch (OAuthProblemException e) {
            log.error(e.getMessage(), e);
            oAuthResponse = OAuthASResponse.errorResponse(401).error(e).buildJSONMessage();
        }
        response.setStatus(oAuthResponse.getResponseStatus());
        PrintWriter writer = response.getWriter();
        writer.println(oAuthResponse.getBody());
        writer.flush();
        writer.close();
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response)throws IOException,OAuthSystemException {
        String clientId = request.getParameter("client_id");
        String refreshToken = request.getParameter("refresh_token");
        OAuthResponse oAuthResponse;
        try {
            if (StringUtils.isEmpty(clientId)) {
                throw OAuthProblemException.error(OAuth2Status.CLIENT_ID_NULL.getMsg()).state(OAuth2Status.CLIENT_ID_NULL.getStatusCode().toString());
            }
            if(StringUtils.isEmpty(refreshToken)){
                throw OAuthProblemException.error(OAuth2Status.REFRESH_TOKEN_NULL.getMsg()).state(OAuth2Status.REFRESH_TOKEN_NULL.getStatusCode().toString());
            }
            OauthClient oauthClient = oauthClientService.isExistClient(clientId);
            if(oauthClient == null){
                throw OAuthProblemException.error(OAuth2Status.CLIENT_NOT_EXIST.getMsg()).state(OAuth2Status.CLIENT_NOT_EXIST.getStatusCode().toString());
            }
            OauthToken oauthToken = oauthTokenService.checkRefreshToken(oauthClient.getId(),refreshToken);
            if(oauthToken == null){
                throw OAuthProblemException.error(OAuth2Status.REFRESH_TOKEN_ERROR.getMsg()).state(OAuth2Status.REFRESH_TOKEN_ERROR.getStatusCode().toString());
            }
            //refresh_token的过期时间,与当前服务器时间做比较
            LocalDateTime refreshExpiryDate = oauthToken.getRefreshExpiryDate();
            if(refreshExpiryDate != null && refreshExpiryDate.isBefore(LocalDateTime.now())){
                throw OAuthProblemException.error(OAuth2Status.REFRESH_TOKEN_EXPIRY.getMsg()).state(OAuth2Status.REFRESH_TOKEN_EXPIRY.getStatusCode().toString());
            }
            OAuthIssuer oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
            String accessToken = oAuthIssuer.accessToken();
            oauthTokenService.updateAccessToken(oauthToken.getId(),accessToken);
            oAuthResponse = OAuthASResponse.tokenResponse(200).setAccessToken(accessToken).buildJSONMessage();
        } catch (OAuthProblemException e) {
            log.error(e.getMessage(),e);
            oAuthResponse = OAuthASResponse.errorResponse(401).error(e).setState(e.getState()).buildJSONMessage();
        }
        response.setStatus(oAuthResponse.getResponseStatus());
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(oAuthResponse.getBody());
        writer.flush();
        writer.close();
    }

}
