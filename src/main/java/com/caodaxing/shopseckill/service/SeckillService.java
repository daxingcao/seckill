package com.caodaxing.shopseckill.service;

import java.util.List;

import com.caodaxing.shopseckill.dto.SeckillResult;
import com.caodaxing.shopseckill.dto.SeckillToken;
import com.caodaxing.shopseckill.entity.Shop;
import com.caodaxing.shopseckill.entity.ShopOrder;
import com.caodaxing.shopseckill.exception.CloseSeckillException;
import com.caodaxing.shopseckill.exception.RepeatSeckillException;
import com.caodaxing.shopseckill.exception.SeckillException;

/**
 * @desription 秒杀相关接口接口
 * @time 2018-11-14
 * @version 1.0.0
 * @author daxing.cao
 *
 */
public interface SeckillService {

	/**
	 * 得到商品信息
	 * 
	 * @param shopCode
	 *            商品编号
	 * @return {@link Shop}对象
	 */
	public Shop queryShop(String shopCode);

	/**
	 * 查询商品列表
	 * 
	 * @param isSeckill
	 *            是否为秒杀商品;0.不参与秒杀,1.参与秒杀
	 * @return 得到一个{@link Shop} 的列表
	 */
	List<Shop> shopList(Integer isSeckill);

	/**
	 * 获取秒杀商品的token,该token用于获取秒杀地址,以及进行秒杀验证
	 * 
	 * @param shopCode
	 *            商品编号
	 * @return 返回一个{@link SeckillToken} 实体对象;
	 */
	SeckillToken getSecKillToken(String shopCode);

	/**
	 * 执行秒杀接口,该接口实现秒杀操作,并且生成秒杀商品订单{@link ShopOrder}
	 * 
	 * @param shopCode
	 *            商品编号
	 * @param userId
	 *            用户id
	 * @param token
	 *            秒杀商品token
	 * @return 秒杀返回结果{@link SeckillResult}
	 * @throws {@link
	 *             SeckillException},{@link RepeatSeckillException},{@link CloseSeckillException}
	 */
	SeckillResult executeSecKill(String shopCode, Long userId, String token)
			throws SeckillException;

}
