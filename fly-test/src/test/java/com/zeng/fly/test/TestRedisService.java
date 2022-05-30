package com.zeng.fly.test;

import com.zeng.fly.facade.api.CacheService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zengxingdeng
 * @data 2022/4/1
 * @des
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestHotService.class)
@EnableAutoConfiguration
@EnableDiscoveryClient//开启服务注册发现功能
public class TestRedisService {

    @DubboReference(version = "1.0")
    private CacheService cacheService;

    @Test
    public void testSaveCache() {
        cacheService.saveRedisCache("zeng:user:id:2", String.valueOf(2));
    }
}
