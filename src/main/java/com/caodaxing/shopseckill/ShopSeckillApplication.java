package com.caodaxing.shopseckill;

import com.caodaxing.dubbo.factory.ProducerRegisterFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author daxing.cao
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.caodaxing.shopseckill.dao")
public class ShopSeckillApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopSeckillApplication.class, args);
	}

	@Bean(initMethod = "init")
	public ProducerRegisterFactory getProducerFactory(){
		ProducerRegisterFactory factory = new ProducerRegisterFactory();
		factory.setRegisterHost("127.0.0.1");
		factory.setRegisterPort(9080);
		factory.setProducerPort(28084);
		return factory;
	}

}
