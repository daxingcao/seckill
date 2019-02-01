package com.caodaxing.shopseckill.security;

import com.caodaxing.shopseckill.autoconfigure.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author daxing.cao
 */
@Slf4j
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(SystemProperties systemProperties){
        Assert.notNull(systemProperties,"SystemProperties is must not null!");
        String locationPath = systemProperties.getMultipartPath();
        MultipartConfigFactory factory = new MultipartConfigFactory();
        log.info(locationPath);
        File file = new File(locationPath);
        if(!file.exists()){
            file.mkdirs();
        }
        factory.setLocation(locationPath);
        return factory.createMultipartConfig();
    }

}
