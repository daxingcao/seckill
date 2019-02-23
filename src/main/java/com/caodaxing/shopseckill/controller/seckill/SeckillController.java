package com.caodaxing.shopseckill.controller.seckill;


import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caodaxing.shopseckill.common.enums.SeckillStatus;
import com.caodaxing.shopseckill.dto.Returns;
import com.caodaxing.shopseckill.dto.SeckillResult;
import com.caodaxing.shopseckill.dto.SeckillToken;
import com.caodaxing.shopseckill.entity.Shop;
import com.caodaxing.shopseckill.exception.CloseSeckillException;
import com.caodaxing.shopseckill.exception.RepeatSeckillException;
import com.caodaxing.shopseckill.exception.SeckillException;
import com.caodaxing.shopseckill.service.SeckillService;
import com.caodaxing.shopseckill.utils.SessionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author daxing.cao
 * @description 秒杀商品控制类
 */
@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/nowTime", method = RequestMethod.GET)
	@ResponseBody
	public Returns<Long> nowTime() {
		Date date = new Date();
		return new Returns<Long>(true, date.getTime());
	}

	@RequestMapping(value = "/x0{isSeckill}/shopList", method = RequestMethod.GET)
	public String getShopList(@PathVariable(name = "isSeckill", required = false) Integer isSeckill, Model model) {
		log.info("跳转至商品列表页面,当isSeckill等于0时,为正常商品页面,等于1时,为秒杀商品页面;isSeckill:{}", isSeckill);
		List<Shop> shopList = seckillService.shopList(isSeckill);
		model.addAttribute("shopList", shopList);
		return "/shop/shop_list";
	}

	@RequestMapping(value = "/{shopCode}/detail", method = RequestMethod.GET)
	public String shopDetail(@PathVariable("shopCode") String shopCode, Model model) {
		Shop queryShop = seckillService.queryShop(shopCode);
		model.addAttribute("shop", queryShop);
		return "/shop/shop_detail";
	}

	@RequestMapping(value = "/{shopCode}/getToken", method = RequestMethod.POST)
	@ResponseBody
	public Returns<SeckillToken> getShopToken(@PathVariable("shopCode") String shopCode) {
		SeckillToken secKillToken = seckillService.getSecKillToken(shopCode);
		return new Returns<SeckillToken>(true, secKillToken);
	}

	@RequestMapping(value = "/{shopCode}/{token}/executeSeckill", method = RequestMethod.POST)
	@ResponseBody
	public Returns<SeckillResult> executeSeckill(@PathVariable("shopCode") String shopCode,
			@PathVariable("token") String token, HttpServletRequest request) {
		if (token == null) {
			return new Returns<>(false, SeckillStatus.TOKEN_ERROR.getStatusInfo());
		}
		
		try {
			Long userId = SessionUtil.getLoginUserId();
			if (userId == null) {
				return new Returns<>(false, "未登录!");
			}
			SeckillResult executeSecKill = seckillService.executeSecKill(shopCode, userId, token);
			return new Returns<SeckillResult>(true, executeSecKill, SeckillStatus.SUCCESS.getStatusInfo());
		} catch (RepeatSeckillException e) {
			log.warn(e.getMessage());
			return new Returns<>(false, SeckillStatus.REPEAT.getStatusInfo());
		} catch (CloseSeckillException e) {
			log.warn(e.getMessage());
			return new Returns<>(false, SeckillStatus.END.getStatusInfo());
		} catch (SeckillException e) {
			log.warn(e.getMessage());
			return new Returns<>(false, SeckillStatus.INNER_ERROR.getStatusInfo());
		}
	}

}
