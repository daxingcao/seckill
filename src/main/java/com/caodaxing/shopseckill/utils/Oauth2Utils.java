package com.caodaxing.shopseckill.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.tomcat.util.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author daxing.cao
 */
public class Oauth2Utils {

    private static ExecutorService executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2014), new ThreadFactoryBuilder().setNameFormat("demo-%d").build());

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        String url = "www.baidu.com";
        //拼接验证信息
        String authInfo = "test" + ":" + "123456";
        //进行base64编码
        String encodeInfo = Base64.encodeBase64String(authInfo.getBytes());
//        String newUrl = url + "?grant_type=client_credentials";
        // 设置授权的信息
        // 注意:Basic后面有一个空格,不然识别不了clientId和clientSecret,会报错
        HttpRequestUtil.builder().setHeader("authorization","Basic " + encodeInfo).setParameter("grant_type","client_credentials");
//        executor.execute(() -> HttpRequestUtil.toPost(url));
        Future<String> submit = executor.submit(() -> HttpRequestUtil.toPost(url));
        executor.shutdown();
        executor.awaitTermination(10,TimeUnit.MINUTES);
        HttpRequestUtil.toPost(url);
//        String refreshUrl = "http://localhost:8080/oauth/refreshToken?client_id=test&refresh_token=f55a6ae330536850d154ef5b91da9aa6";
//        String result = HttpRequestUtil.toGet(refreshUrl);
    }

}
