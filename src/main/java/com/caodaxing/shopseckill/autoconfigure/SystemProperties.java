package com.caodaxing.shopseckill.autoconfigure;

import com.caodaxing.shopseckill.autoconfigure.include.LanguageInclude;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author daxing.cao
 * @description 系统配置类
 */
@Component
@ConfigurationProperties(
        prefix = "system.config"
)
public class SystemProperties {

    private String salt;
    private String multipartPath;
    private String freemarkerImport;
    private Auth auth;
    private Shiro shiro;

    public SystemProperties(){
        this.salt = "";
        this.multipartPath = System.getProperty("user.dir")+"/data/tmp";
        this.auth = new SystemProperties.Auth();
        this.shiro = new SystemProperties.Shiro();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMultipartPath() {
        return multipartPath;
    }

    public void setMultipartPath(String multipartPath) {
        this.multipartPath = multipartPath;
    }

    public String getFreemarkerImport() {
        return freemarkerImport;
    }

    public void setFreemarkerImport(String freemarkerImport) {
        this.freemarkerImport = freemarkerImport;
    }

    public SystemProperties.Auth getAuth() {
        return this.auth;
    }

    public SystemProperties.Shiro getShiro() {
        return this.shiro;
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
