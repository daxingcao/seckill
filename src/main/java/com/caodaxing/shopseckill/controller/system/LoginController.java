package com.caodaxing.shopseckill.controller.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.entity.LoginUser;
import com.caodaxing.shopseckill.entity.LoginUserExample;
import com.caodaxing.shopseckill.service.LoginUserService;
import com.caodaxing.shopseckill.utils.MD5Utils;
import com.caodaxing.shopseckill.utils.MessageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author daxing.cao
 * @description 验证登录控制类
 */
@Slf4j
@Controller
@RequestMapping("/system")
public class LoginController {
	
	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private ShiroFilterFactoryBean shiroFilter;
	
	@PostMapping("/checkLogin.do")
	@ResponseBody
	public Map<String, Object> checkLogin(LoginUser loginUser,HttpServletRequest request,HttpServletResponse response){
		log.info("验证登录信息开始!");
		if(StringUtils.isEmpty(loginUser.getUsername()) || StringUtils.isEmpty(loginUser.getPassword())) {
			return MessageUtil.errorMessage("用户名或密码不能为空!");
		}
		String encryptedPassword = MD5Utils.getMD5(loginUser.getPassword());
		LoginUserExample example = new LoginUserExample();
		example.createCriteria().andUsernameEqualTo(loginUser.getUsername());
		List<LoginUser> loginUserList = loginUserService.selectByExample(example);
		if(loginUserList.size() > 0) {
			loginUser = loginUserList.get(0);
			if(!StringUtils.equals(encryptedPassword, loginUser.getPassword())) {
				return MessageUtil.errorMessage("密码错误!");
			}
			HttpSession session = request.getSession(true);
			session.setAttribute(SystemFiled.USER_SESSION, loginUser);
			Subject sub = SecurityUtils.getSubject();
			UsernamePasswordToken upt = new UsernamePasswordToken(loginUser.getUsername(),encryptedPassword);
			sub.login(upt);
			//拿到上一次访问的地址,没有则跳转到successUrl
			SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
			String prevRequest = null;
			if(savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
				prevRequest = savedRequest.getRequestUrl();
				if(prevRequest.endsWith(shiroFilter.getLoginUrl())|| this.checkSaveUrl(prevRequest)) {
					prevRequest = null;
				}
			}
			if(prevRequest == null) {
				prevRequest = shiroFilter.getSuccessUrl();
			}
			return MessageUtil.successMessage((Object)prevRequest);
		}
		return MessageUtil.errorMessage("该用户名不存在!");
	}

	private boolean checkSaveUrl(String url){
		return StringUtils.equals("/",url) || StringUtils.equals("/favicon.ico", url);
	}

}
