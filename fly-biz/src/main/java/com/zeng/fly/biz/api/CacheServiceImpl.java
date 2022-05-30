package com.zeng.fly.biz.api;

import com.zeng.fly.common.redis.RedisService;
import com.zeng.fly.facade.api.CacheService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author zengxingdeng
 * @data 2022/4/6
 * @des
 */
@DubboService(version = "1.0")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;


    @Override
    public void saveRedisCache(String key, String value) {
        redisService.setCacheObject(key, value, 10L, TimeUnit.SECONDS);
    }

    @Override
    public void testBatchPop() {
        redisService.setCacheObject("123456789", 1, 10L, TimeUnit.SECONDS);
    }
}
