package com.caodaxing.shopseckill.controller.mongo.shop;

import com.caodaxing.shopseckill.entity.Shop;
import com.caodaxing.shopseckill.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author daxing.cao
 * @version 1.0
 *
 * @description mongodb shop控制类
 */
@RestController
@RequestMapping("/mongo/shop")
public class ShopController {

    @Autowired
    private MongoService mongoService;

    @GetMapping("/saveShop.do")
    public String saveShop(){
        Shop shop = Shop.builder().id(12345678L).shopCode("dsfagdfef").shopName("caodaxing").shopPrice(new BigDecimal("1000000000000"))
                .createTime(new Date()).build();
        mongoService.insert(shop);
        return "插入成功！";
    }

    @GetMapping("getShop.do")
    public Shop getShop(Long id){
        return mongoService.getShop(id);
    }

}
