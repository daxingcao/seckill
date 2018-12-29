package com.caodaxing.shopseckill.controller.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;

@Controller
@RequestMapping("/oauth")
public class Authorization2Controller {

    public void test(){
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.isAfter(LocalDateTime.now());
    }

}
