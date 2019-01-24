package com.caodaxing.shopseckill.controller.oauth2.view;

import com.caodaxing.shopseckill.common.SystemFiled;
import com.caodaxing.shopseckill.dto.PageSearcher;
import com.caodaxing.shopseckill.entity.OauthClient;
import com.caodaxing.shopseckill.service.oauth2.OauthClientService;
import com.caodaxing.shopseckill.utils.MD5Utils;
import com.caodaxing.shopseckill.utils.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/client")
public class ClientOperateController {

    @Autowired
    private OauthClientService oauthClientService;

    @GetMapping("/index")
    public String index() {
        return "/oauth2/index";
    }

    @RequestMapping("/clientList.do")
    @ResponseBody
    public Map<String, Object> clientList(@RequestBody PageSearcher<OauthClient> data){
        PageHelper.startPage(data.getPageNumber(),data.getPageSize());
        List<OauthClient> lists = oauthClientService.getListByParams(data.getData());
        PageInfo pageInfo = new PageInfo(lists);
        Map<String, Object> param = Maps.newHashMap();
        param.put("total",pageInfo.getTotal());
        param.put("rows",pageInfo.getList());
        return param;
    }

    @PostMapping("/addClient.do")
    @ResponseBody
    public Map<String, Object> addClient(OauthClient oauthClient){
        if(oauthClient == null){
            return MessageUtil.errorMessage("添加应用失败,数据为空!","101");
        }
        OauthClient isExist = oauthClientService.isExistClient(oauthClient.getClientId());
        if(isExist != null){
            return MessageUtil.errorMessage("该应用ID已存在!","102");
        }
        //对client_secret进行加密
        String newSecret = MD5Utils.getMD5(oauthClient.getClientSecret());
        oauthClient.setClientSecret(newSecret);
        System.out.println(LocalDateTime.now());
        oauthClient.setExpiryDate(LocalDateTime.of(2099,Month.DECEMBER,31,23,59,59));
        oauthClientService.insertSelective(oauthClient);
        return MessageUtil.successMessage("添加成功!");
    }

    @PostMapping("/batchDeleteClient.do")
    @ResponseBody
    public Map<String, Object> batchDeleteClient(@RequestBody ArrayList<Long> idList){
        if(idList == null){
            return MessageUtil.errorMessage("数据为空!");
        }
        oauthClientService.batchDeleteById(idList);
        return MessageUtil.successMessage("删除成功!");
    }

    @PostMapping("/updateClient.do")
    @ResponseBody
    public Map<String, Object> updateClient(OauthClient oauthClient){
        if(oauthClient == null || oauthClient.getId() == null){
            return MessageUtil.errorMessage("数据为空!");
        }
        oauthClientService.updateByPrimaryKeySelective(oauthClient);
        return MessageUtil.successMessage("更新成功!");
    }

}
