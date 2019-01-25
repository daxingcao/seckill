package com.caodaxing.shopseckill.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Slf4j
@Configuration
public class MultipartConfig {

    @Value("${spring.location.path}")
    private String location_path;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        String location = System.getProperty("user.dir") + "/data/tmp";
        log.info(location_path);
        File file = new File(location_path);
        if(!file.exists()){
            file.mkdirs();
        }
        factory.setLocation(location_path);
        return factory.createMultipartConfig();
    }

}
