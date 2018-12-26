package com.caodaxing.shopseckill.service.oauth2.impl;

import com.caodaxing.shopseckill.dao.OauthClientMapper;
import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthClientExample;
import com.caodaxing.shopseckill.service.oauth2.OauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthClientServiceImpl implements OauthClientService {

    @Autowired
    private OauthClientMapper oauthClientMapper;

    @Override
    public long countByExample(OauthClientExample example) {
        return oauthClientMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(OauthClientExample example) {
        return oauthClientMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return oauthClientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OauthClient record) {
        return oauthClientMapper.insert(record);
    }

    @Override
    public int insertSelective(OauthClient record) {
        return oauthClientMapper.insertSelective(record);
    }

    @Override
    public List<OauthClient> selectByExample(OauthClientExample example) {
        return oauthClientMapper.selectByExample(example);
    }

    @Override
    public OauthClient selectByPrimaryKey(Long id) {
        return oauthClientMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(OauthClient record, OauthClientExample example) {
        return oauthClientMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(OauthClient record, OauthClientExample example) {
        return oauthClientMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(OauthClient record) {
        return oauthClientMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OauthClient record) {
        return oauthClientMapper.updateByPrimaryKey(record);
    }
}
