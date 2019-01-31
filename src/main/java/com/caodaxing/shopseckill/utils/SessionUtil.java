package com.caodaxing.shopseckill.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.entity.LoginUser;
/**
 * @author daxing.cao
 */
public class SessionUtil {
	
	public static LoginUser getUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute(SystemFiled.USER_SESSION);
		if(obj == null) {
			return null;
		}
		return (LoginUser) obj;
	}

	public static Long getLoginUserId(HttpServletRequest request) {
		LoginUser userInfo = getUserInfo(request);
		if(userInfo == null) {
			return null;
		}
		return userInfo.getId();
	}

}
