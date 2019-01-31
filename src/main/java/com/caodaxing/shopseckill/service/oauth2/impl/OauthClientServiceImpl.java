package com.caodaxing.shopseckill.service.oauth2.impl;

import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.dao.OauthClientMapper;
import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.entity.OauthClientExample;
import com.caodaxing.shopseckill.service.oauth2.OauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author daxing.cao
 */
@Service
public class OauthClientServiceImpl implements OauthClientService {

    @Autowired
    private OauthClientMapper oauthClientMapper;

    @Override
    public long countByExample(OauthClientExample example) {
        return oauthClientMapper.countByExample(example);
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
    public int updateByPrimaryKeySelective(OauthClient record) {
        return oauthClientMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OauthClient record) {
        return oauthClientMapper.updateByPrimaryKey(record);
    }

    @Override
    public OauthClient checkClient(String clientId, String clientSecret) {
        OauthClientExample example = new OauthClientExample();
        example.createCriteria().andClientIdEqualTo(clientId).andClientSecretEqualTo(clientSecret).andIsDeleteEqualTo(SystemFiled.DeleteStatus.NOT_DELETED.getCode());
        List<OauthClient> lists = this.selectByExample(example);
        if(lists != null && !lists.isEmpty()){
            return lists.get(0);
        }
        return null;
    }

    @Override
    public OauthClient isExistClient(String clientId) {
        OauthClientExample example = new OauthClientExample();
        example.createCriteria().andClientIdEqualTo(clientId).andIsDeleteEqualTo(SystemFiled.DeleteStatus.NOT_DELETED.getCode());
        List<OauthClient> list = this.selectByExample(example);
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
