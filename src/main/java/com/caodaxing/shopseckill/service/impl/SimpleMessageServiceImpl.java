package com.caodaxing.shopseckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caodaxing.shopseckill.dao.SimpleMessageMapper;
import com.caodaxing.shopseckill.entity.SimpleMessage;
import com.caodaxing.shopseckill.service.SimpleMessageService;
/**
 * @author daxing.cao
 */
@Service
public class SimpleMessageServiceImpl implements SimpleMessageService {

	@Autowired
	private SimpleMessageMapper simpleMessageDao;
	
	@Override
	public SimpleMessage queryMsg(String id) {
		return simpleMessageDao.selectByPrimaryKey(id);
	}

}
