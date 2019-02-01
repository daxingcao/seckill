package com.caodaxing.shopseckill.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author daxing.cao
 */
@Component
public class TestJob {

    private int sum = 0;

    @Scheduled(cron = "0/10 50-59 15 * * *")
    public void test(){
        sum++;
        if(sum%3 == 0){
            throw new RuntimeException(Thread.currentThread().getName()+":异常");
        }
        System.out.printf("当前数量:%s",sum);
    }

}
