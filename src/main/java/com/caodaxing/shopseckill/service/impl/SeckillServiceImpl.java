package com.caodaxing.shopseckill.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.caodaxing.shopseckill.autoconfigure.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.caodaxing.shopseckill.common.enums.SeckillStatus;
import com.caodaxing.shopseckill.dao.ShopMapper;
import com.caodaxing.shopseckill.dao.ShopOrderMapper;
import com.caodaxing.shopseckill.dto.SeckillResult;
import com.caodaxing.shopseckill.dto.SeckillToken;
import com.caodaxing.shopseckill.entity.Shop;
import com.caodaxing.shopseckill.entity.ShopOrder;
import com.caodaxing.shopseckill.exception.CloseSeckillException;
import com.caodaxing.shopseckill.exception.RepeatSeckillException;
import com.caodaxing.shopseckill.exception.SeckillException;
import com.caodaxing.shopseckill.service.SeckillService;

import lombok.extern.slf4j.Slf4j;
/**
 * @author daxing.cao
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

	@Autowired
	private SystemProperties properties;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private ShopOrderMapper shopOrderMapper;

	@Override
	public Shop queryShop(String shopCode) {
		return shopMapper.queryShop(shopCode);
	}

	@Override
	public List<Shop> shopList(Integer isSeckill) {
		return shopMapper.shopList(isSeckill);
	}

	@Override
	public SeckillToken getSecKillToken(String shopCode) {
		Shop queryShop = shopMapper.queryShop(shopCode);
		if (queryShop == null) {
			return new SeckillToken(false, shopCode);
		}
		long nowTime = Instant.now().toEpochMilli();
		long startTime = queryShop.getStartTime().getTime();
		long endTime = queryShop.getEndTime().getTime();
		// 判断该秒杀商品是否未开始或者已结束
		if (nowTime < startTime || nowTime > endTime) {
			return new SeckillToken(false, nowTime, startTime, endTime);
		}
		// 生成该秒杀商品对应的token
		String token = generateToken(shopCode);
		return new SeckillToken(true, shopCode, token);
	}

	@Override
	@Transactional(rollbackFor = SeckillException.class)
	public SeckillResult executeSecKill(String shopCode, Long userId, String token)
			throws SeckillException {
		try {
			if (token == null || !token.equals(generateToken(shopCode))) {
				throw new SeckillException("the shop token is compare failura,token may be modified !");
			}
			int updateCount = shopMapper.seckillExecution(shopCode, new Date());
			if (updateCount <= 0) {
				throw new CloseSeckillException("the seckill shopp is closed!");
			} else {
				String orderCode = UUID.randomUUID().toString().replaceAll("-", "");
				int insert = shopOrderMapper.insert(new ShopOrder(userId, shopCode, orderCode));
				if (insert <= 0) {
					throw new RepeatSeckillException("the shop is repeated buy!");
				} else {
					ShopOrder queryShopOrder = shopOrderMapper.queryShopOrder(shopCode, userId);
					return new SeckillResult(shopCode, SeckillStatus.SUCCESS, queryShopOrder);
				}
			}
		} catch (CloseSeckillException e) {
			throw e;
		} catch (RepeatSeckillException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}

	private String generateToken(String shopCode) {
		String originalToken = properties.getSalt() + "/" + shopCode;
		String token = DigestUtils.md5DigestAsHex(originalToken.getBytes());
		return token;
	}

}
