package com.zeng.fly.common.redis.template;

/**
 * @author cuixiaochun
 * @date 2021年11月30日
 */
public interface TryLockHandler<IN, OUT> {

    /**
     * 获得锁的处理
     *
     * @param in
     * @return
     */
    OUT doTryLockTrue(IN in);

    /**
     * 未获得锁的处理
     *
     * @param in
     * @return
     */
    OUT doTryLockFalse(IN in);
}
