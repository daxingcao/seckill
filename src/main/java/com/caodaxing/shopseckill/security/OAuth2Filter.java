package com.caodaxing.shopseckill.security;

import com.alibaba.fastjson.JSON;
import com.caodaxing.shopseckill.common.enums.OAuth2Status;
import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.exception.oauth2.OAuth2Exception;
import com.caodaxing.shopseckill.exception.oauth2.OAuth2TokenExpiryException;
import com.caodaxing.shopseckill.exception.oauth2.OAuth2TokenNullException;
import com.caodaxing.shopseckill.exception.oauth2.OAuth2TokenValidException;
import com.caodaxing.shopseckill.service.oauth2.OauthTokenService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Slf4j
public class OAuth2Filter extends AccessControlFilter {

    @Autowired
    private OauthTokenService oauthTokenService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        HttpServletResponse response = WebUtils.toHttp(servletResponse);
        response.setContentType("application/json;charset=utf-8");
        try {
            String accessToken = request.getParameter("access_token");
            if(accessToken == null){
                throw new OAuth2TokenNullException(OAuth2Status.TOKEN_ISNULL.getMsg());
            }
            OauthToken oauthToken = oauthTokenService.queryOauthTokenBy(accessToken);
            if (oauthToken == null) {
                throw new OAuth2TokenValidException(OAuth2Status.TOKEN_ERROR.getMsg());
            }
            LocalDateTime expiryDate = oauthToken.getExpiryDate();
            if (expiryDate != null && expiryDate.isBefore(LocalDateTime.now())) {
                throw new OAuth2TokenExpiryException(OAuth2Status.TOKEN_EXPIRY.getMsg());
            }
        } catch (OAuth2TokenValidException e) {
            log.info(e.getMessage());
            this.responseMessage(response,JSON.toJSONString(OAuth2Status.TOKEN_ERROR.toReturns()),401);
            return false;
        } catch (OAuth2TokenExpiryException e) {
            log.info(e.getMessage());
            this.responseMessage(response,JSON.toJSONString(OAuth2Status.TOKEN_EXPIRY.toReturns()),401);
            return false;
        } catch (OAuth2TokenNullException e){
            log.info(e.getMessage());
            this.responseMessage(response,JSON.toJSONString(OAuth2Status.TOKEN_ISNULL.toReturns()), 400);
            return false;
        }catch (Exception e) {
            log.error(e.getMessage(),e);
            this.responseMessage(response,JSON.toJSONString(OAuth2Status.SYSTEM_ERROR.toReturns()),409);
            return false;
        }
        return true;
    }

    private void responseMessage(HttpServletResponse response, String body,Integer status) throws Exception {
        response.setStatus(status);
        PrintWriter writer = response.getWriter();
        writer.println(body);
        writer.flush();
        writer.close();
    }

//    private static List<String> list = Lists.newArrayList();

//    public static void main(String[] args){
//        list.add("aaa");
//        list.add("bbb");
//        String s = "bbb";
//        System.out.println(list.contains(s));
//        list.remove("bbb");
//        System.out.println(JSON.toJSONString(list,true));
//        System.out.println(JSON.toJSONString(OAuth2Status.TOKEN_ERROR.toReturns()));
//    }
}
