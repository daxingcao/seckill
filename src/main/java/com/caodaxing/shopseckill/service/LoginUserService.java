package com.caodaxing.shopseckill.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caodaxing.shopseckill.entity.LoginUser;
import com.caodaxing.shopseckill.entity.LoginUserExample;

public interface LoginUserService {
	long countByExample(LoginUserExample example);

    int insert(LoginUser record);

    int insertSelective(LoginUser record);

    List<LoginUser> selectByExample(LoginUserExample example);

    LoginUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginUser record);

    int updateByPrimaryKey(LoginUser record);
}
