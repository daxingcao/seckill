package com.caodaxing.shopseckill.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daxing.cao
 * @description 获取spring上下文工具类
 */
public class WebApplicationUtils {

    public static ApplicationContext getApplicationContext(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    }

    public static Object getBean(String beanName){
        return getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> beanClazz){
        return getApplicationContext().getBean(beanName, beanClazz);
    }

}
