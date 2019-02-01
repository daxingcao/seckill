package com.caodaxing.shopseckill.security;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caodaxing.shopseckill.dao.LoginUserMapper;
import com.caodaxing.shopseckill.entity.LoginUser;
import com.caodaxing.shopseckill.entity.LoginUserExample;

/**
 * @author daxing.cao
 */
public class BaseAuthRealm extends AuthorizingRealm {
	
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	/**
	 * 验证登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String userName = upToken.getUsername();
		char[] password = upToken.getPassword();
		if(!StringUtils.isEmpty(userName) && password != null ){
			return new SimpleAuthenticationInfo(userName, password, getName());
		}
		return null;
	}

	/**
	 * 验证角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		/* 该取值为上面方法返回对象设置的第一个参数,
		例:new SimpleAuthenticationInfo(loginUser.getUsername(), loginUser.getPassword(), getName()),
		得到的参数为loginUser.getUsername()的参数 */
		String loginName = (String)getAvailablePrincipal(principal);
		if(loginName == null) {
			return null;
		}
		//查询该用户信息
		LoginUserExample example = new LoginUserExample();
		example.createCriteria().andUsernameEqualTo(loginName);
		List<LoginUser> list = loginUserMapper.selectByExample(example);
		if(list.size() > 0) {
			SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
			//该用户的拥有的角色列表
			List<String> roles = new ArrayList<>();
			//该角色拥有的权限
			List<String> auths = new ArrayList<>();
			//查询角色列表
			//TODO 查询数据该用户拥有的角色和权限,加入进SimpleAuthorizationInfo类中
			//将角色和权限加入进SimpleAuthorizationInfo中
			sai.addRoles(roles);
			sai.addStringPermissions(auths);
			return sai;
		}
		return null;
	}

}
