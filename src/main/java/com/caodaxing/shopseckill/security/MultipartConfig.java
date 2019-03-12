package com.caodaxing.shopseckill.security;

import com.caodaxing.shopseckill.autoconfigure.SystemProperties;
import lombok.extern.slf4j.Slf4j;
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
        String basePath = System.getProperty("user.dir") + "/" + systemProperties.getMultipartPath();
        System.out.println(basePath);
        MultipartConfigFactory factory = new MultipartConfigFactory();
        log.info(basePath);
        File file = new File(basePath);
        if(!file.exists()){
            file.mkdirs();
        }
        factory.setLocation(basePath);
        return factory.createMultipartConfig();
    }

}
