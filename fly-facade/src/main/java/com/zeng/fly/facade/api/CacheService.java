package com.zeng.fly.facade.api;

/**
 * @author zengxingdeng
 * @data 2022/4/6
 * @des
 */
public interface CacheService {

    void saveRedisCache(String key, String value);

    void testBatchPop();
}
