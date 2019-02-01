package com.caodaxing.shopseckill.dao;

import com.caodaxing.shopseckill.entity.LoginUser;
import com.caodaxing.shopseckill.entity.LoginUserExample;
import java.util.List;

/**
 * @author daxing.cao
 */
public interface LoginUserMapper {
    long countByExample(LoginUserExample example);

    int insert(LoginUser record);

    int insertSelective(LoginUser record);

    List<LoginUser> selectByExample(LoginUserExample example);

    LoginUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginUser record);

    int updateByPrimaryKey(LoginUser record);
}