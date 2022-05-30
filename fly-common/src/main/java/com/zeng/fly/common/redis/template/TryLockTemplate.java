package com.zeng.fly.common.redis.template;

/**
 * @author cuixiaochun
 * @date 2021年11月30日
 */
public interface TryLockTemplate {

    <IN, OUT> OUT tryLock(String key, IN in, TryLockHandler<IN, OUT> handler);

}
