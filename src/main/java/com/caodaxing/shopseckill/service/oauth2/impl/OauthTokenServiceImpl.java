package com.caodaxing.shopseckill.service.oauth2.impl;

import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.dao.OauthTokenMapper;
import com.caodaxing.shopseckill.entity.OauthToken;
import com.caodaxing.shopseckill.entity.OauthTokenExample;
import com.caodaxing.shopseckill.service.oauth2.OauthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthTokenServiceImpl implements OauthTokenService {

    @Autowired
    private OauthTokenMapper oauthTokenMapper;

    @Override
    public long countByExample(OauthTokenExample example) {
        return oauthTokenMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(OauthTokenExample example) {
        return oauthTokenMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return oauthTokenMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OauthToken record) {
        return oauthTokenMapper.insert(record);
    }

    @Override
    public int insertSelective(OauthToken record) {
        return oauthTokenMapper.insertSelective(record);
    }

    @Override
    public List<OauthToken> selectByExample(OauthTokenExample example) {
        return oauthTokenMapper.selectByExample(example);
    }

    @Override
    public OauthToken selectByPrimaryKey(Long id) {
        return oauthTokenMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(OauthToken record, OauthTokenExample example) {
        return oauthTokenMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(OauthToken record, OauthTokenExample example) {
        return oauthTokenMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(OauthToken record) {
        return oauthTokenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OauthToken record) {
        return oauthTokenMapper.updateByPrimaryKey(record);
    }

    @Override
    public OauthToken queryOauthTokenBy(String accessToken) {
        OauthTokenExample example = new OauthTokenExample();
        example.createCriteria().andAccessTokenEqualTo(accessToken).andIsDeleteEqualTo(SystemFiled.DeleteStatus.NOT_DELETED.getCode());
        List<OauthToken> lists = selectByExample(example);
        if(lists != null && !lists.isEmpty()){
            return lists.get(0);
        }
        return null;
    }
}
