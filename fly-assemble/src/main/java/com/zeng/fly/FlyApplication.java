package com.zeng.fly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author cuixiaochun
 * @version 2021年8月16日
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册发现功能
@EnableFeignClients
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {FlyApplication.class, RedisMessageListenerContainer.class})
@EnableAsync // 启用@Async
public class FlyApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlyApplication.class, args);
    }
}
