package com.caodaxing.shopseckill.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import com.google.common.collect.Maps;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author daxing.cao
 */
@Configuration
public class ShiroAuthrentitionConfig {

	@Value("${shiro.config.loginUrl}")
	private String loginUrl;
	@Value("${shiro.config.successUrl}")
	private String successUrl;
	
	@Bean(name="baseRealm")
	public AuthorizingRealm authorizatingRealm() {
		return new BaseAuthRealm();
	}
	
	@Bean
	public AuthorizationFilter roleAuthorizationFilter() {
		return new ShiroRolesFilter();
	}

	@Bean
	public AccessControlFilter oauth2Filter(){
		return new OAuth2Filter();
	}
	
	public LogoutFilter logoutFilter() {
		LogoutFilter logoutFilter = new ShiroLogoutFilter();
		logoutFilter.setRedirectUrl("/main/login.jhtml");
		return logoutFilter;
	}
	
	@Bean(name="lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daac = new DefaultAdvisorAutoProxyCreator();
		daac.setProxyTargetClass(true);
		return daac;
	}
	
	@Bean(name="securityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(authorizatingRealm());
		return dwsm;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(defaultWebSecurityManager());
		return aasa;
	}
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(defaultWebSecurityManager());
		shiroFilter.setLoginUrl("/main/login.jhtml");
		//登录成功后跳转的地址
		shiroFilter.setSuccessUrl("/seckill/x0/shopList");
		//配置url的相关权限
		Map<String, String> urlAuth = Maps.newHashMap();
		urlAuth.put("/static/**", "anon");
		urlAuth.put("/html/**", "anon");
		urlAuth.put("/system/checkLogin.do", "anon");
		urlAuth.put("/oauth/**", "anon");
		urlAuth.put("/open/**","oauth2");
		urlAuth.put("/logout", "logout");
		urlAuth.put("/proviter/**", "authc,anyRole[admin,user]");
		urlAuth.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(urlAuth);
		//加载自定义过滤器
		Map<String, Filter> filterMap = Maps.newHashMap();
		filterMap.put("logout", logoutFilter());
		filterMap.put("anyRole", roleAuthorizationFilter());
		filterMap.put("oauth2",  oauth2Filter());
		shiroFilter.setFilters(filterMap);
		return shiroFilter;
	}

}
