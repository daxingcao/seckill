package com.caodaxing.shopseckill.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.entity.LoginUser;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author daxing.cao
 */
public class SessionUtil {
	
	public static LoginUser getUserInfo() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute(SystemFiled.USER_SESSION);
		if(obj == null) {
			return null;
		}
		return (LoginUser) obj;
	}

	public static Long getLoginUserId() {
		LoginUser userInfo = getUserInfo();
		if(userInfo == null) {
			return null;
		}
		return userInfo.getId();
	}

}
