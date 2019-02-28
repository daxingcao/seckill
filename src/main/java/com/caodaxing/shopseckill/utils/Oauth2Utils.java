package com.caodaxing.shopseckill.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.util.Map;
/**
 * @author daxing.cao
 */
public class Oauth2Utils {

    public static String getAccessToken(String url, String clientId, String clientSecret) throws UnsupportedEncodingException {
        //拼接验证信息
        String authInfo = clientId + ":" + clientSecret;
        //进行base64编码
        String encodeInfo = Base64.encodeBase64String(authInfo.getBytes());
//        String newUrl = url + "?grant_type=client_credentials";
        // 设置授权的信息
        // 注意:Basic后面有一个空格,不然识别不了clientId和clientSecret,会报错
        HttpRequestUtil.createBuilder().setHeader("Authorization","Basic " + encodeInfo).setParameter("grant_type","client_credentials");
        String result = HttpRequestUtil.toPost(url);
//        String refreshUrl = "http://localhost:8080/oauth/refreshToken?client_id=test&refresh_token=f55a6ae330536850d154ef5b91da9aa6";
//        String result = HttpRequestUtil.toGet(refreshUrl);
        return result;
    }

}
