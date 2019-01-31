package com.caodaxing.shopseckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caodaxing.shopseckill.service.SeckillService;

/**
 * @author daxing.cao
 * @description 主控制类
 */
@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/{id}/test",method=RequestMethod.GET)
	public String test(@PathVariable("id") String id) {
		return "/system/index";
	}
	
	@RequestMapping("/login.jhtml")
	public String hello(Model model){
		seckillService.shopList(1);
		return "/system/login";
	}
	
}
