package com.caodaxing.shopseckill.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.caodaxing.shopseckill.utils.MessageUtil;
/**
 * @author daxing.cao
 */
public class ShiroRolesFilter extends AuthorizationFilter {
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest req = WebUtils.toHttp(request);
		HttpServletResponse resp = WebUtils.toHttp(response);
		String requestUri = WebUtils.getRequestUri(req);
		if(requestUri.startsWith("/my")) {
			resp.sendRedirect("login.jhtml");
		}else {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			Map<String, Object> message = MessageUtil.errorMessage("未通过身份认证!");
			writer.println(JSONObject.toJSONString(message));
			writer.flush();
			writer.close();
		}
		return false;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		final Subject subject = getSubject(request, response);
		//mappedValue的值就是spring-shiro.xml 过滤器链定义中roles["user,admin"] 括号里面的值
		String[] roleNames = (String[])mappedValue;
		if(roleNames == null || roleNames.length == 0) {
			return true;
		}
		
		for (String role : roleNames) {
			if(subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}
