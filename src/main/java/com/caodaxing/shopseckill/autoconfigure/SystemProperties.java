package com.caodaxing.shopseckill.autoconfigure;

import com.caodaxing.shopseckill.autoconfigure.include.LanguageInclude;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author daxing.cao
 * @description 系统配置类
 */
@Data
@Component
@ConfigurationProperties(
        prefix = "system.config"
)
public class SystemProperties {

    private String salt;
    private String multipartPath;
    private String freemarkerImport;
    private final SystemProperties.Auth auth;
    private final SystemProperties.Shiro shiro;

    SystemProperties(){
        System.out.println("SystemProperties被初始化了");
        this.salt = "";
        this.multipartPath = System.getProperty("user.dir")+"/data/tmp";
        this.auth = new SystemProperties.Auth();
        this.shiro = new SystemProperties.Shiro();
    }

    public static class Auth{

        private LanguageInclude messageLanguage;
        /**
         * unit:minutes
         */
        private Long accessTokenExpiryDate;
        /**
         * unit:day
         */
        private Long refreshTokenExpiryDate;

        public Long getAccessTokenExpiryDate() {
            return accessTokenExpiryDate;
        }

        public void setAccessTokenExpiryDate(Long accessTokenExpiryDate) {
            this.accessTokenExpiryDate = accessTokenExpiryDate;
        }

        public Long getRefreshTokenExpiryDate() {
            return refreshTokenExpiryDate;
        }

        public void setRefreshTokenExpiryDate(Long refreshTokenExpiryDate) {
            this.refreshTokenExpiryDate = refreshTokenExpiryDate;
        }

        public LanguageInclude getMessageLanguage() {
            return messageLanguage;
        }

        public void setMessageLanguage(LanguageInclude messageLanguage) {
            this.messageLanguage = messageLanguage;
        }
    }

    public static class Shiro{

        private String loginUrl;
        private String successUrl;

        public String getLoginUrl() {
            return loginUrl;
        }

        public void setLoginUrl(String loginUrl) {
            this.loginUrl = loginUrl;
        }

        public String getSuccessUrl() {
            return successUrl;
        }

        public void setSuccessUrl(String successUrl) {
            this.successUrl = successUrl;
        }
    }

}
