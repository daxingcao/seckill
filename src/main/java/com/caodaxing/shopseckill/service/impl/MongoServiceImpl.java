package com.caodaxing.shopseckill.service.impl;

import com.caodaxing.shopseckill.entity.Shop;
import com.caodaxing.shopseckill.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author daxing.cao
 * @version 1.0
 *
 * @description mongodb业务逻辑实现类
 */
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insert(Shop shop) {
        mongoTemplate.insert(shop);
        return 0;
    }

    @Override
    public void update(Shop shop) {
        Query query = new Query();
        query.addCriteria(Criteria.where("shopName").is(shop.getShopName()))
                .addCriteria(Criteria.where("shopCode").is(shop.getShopCode()));
        Update update = new Update().addToSet("shopPrice",0.00);
        mongoTemplate.updateFirst(query,update, Shop.class,"shop");
    }

    @Override
    public Shop getShop(Long id) {
        List<Shop> optional = mongoTemplate.find(new Query().addCriteria(Criteria.where("_id").is(id)),Shop.class);
        return optional.get(0);
    }
}
