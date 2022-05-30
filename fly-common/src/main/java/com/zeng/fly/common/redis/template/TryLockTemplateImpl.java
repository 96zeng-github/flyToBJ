package com.zeng.fly.common.redis.template;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis锁
 * @author cuixiaochun
 * @date 2021年11月30日
 */
public class TryLockTemplateImpl implements TryLockTemplate {
    @Autowired
    RedissonClient redissonClient;

    @Override
    public <IN, OUT> OUT tryLock(String key, IN in, TryLockHandler<IN, OUT> handler) {
        RLock lock = redissonClient.getLock(key);
        if (lock.tryLock()) {
            try {
                return handler.doTryLockTrue(in);
            } finally {
                //                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                //                    lock.unlock();
                //                }
                lock.unlock();
            }
        } else {
            return handler.doTryLockFalse(in);
        }
    }

}
