package com.caodaxing.shopseckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caodaxing.shopseckill.dao.LoginUserMapper;
import com.caodaxing.shopseckill.entity.LoginUser;
import com.caodaxing.shopseckill.entity.LoginUserExample;
import com.caodaxing.shopseckill.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	private LoginUserMapper loginUserDao;
	
	@Override
	public long countByExample(LoginUserExample example) {
		return loginUserDao.countByExample(example);
	}

	@Override
	public int deleteByExample(LoginUserExample example) {
		return loginUserDao.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return loginUserDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LoginUser record) {
		return loginUserDao.insert(record);
	}

	@Override
	public int insertSelective(LoginUser record) {
		return loginUserDao.insertSelective(record);
	}

	@Override
	public List<LoginUser> selectByExample(LoginUserExample example) {
		return loginUserDao.selectByExample(example);
	}

	@Override
	public LoginUser selectByPrimaryKey(Long id) {
		return loginUserDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(LoginUser record, LoginUserExample example) {
		return loginUserDao.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(LoginUser record, LoginUserExample example) {
		return loginUserDao.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(LoginUser record) {
		return loginUserDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LoginUser record) {
		return loginUserDao.updateByPrimaryKey(record);
	}

}
