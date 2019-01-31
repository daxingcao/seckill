package com.caodaxing.shopseckill.controller.oauth2;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author daxing.cao
 * @description 对外开放API接口控制类
 */
@Controller
@RequestMapping("/open")
public class ExternalApiController {

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("hello","world");
        return map;
    }

}
