package com.zeng.fly.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @title: CacheUtil
 * @Author: wangxiaodong
 * @Date: 2020/11/11 1:02
 * @Version 1.0
 */
@Component
public class RedisService {

    @Autowired
    public RedisTemplate redisTemplate;

    @PostConstruct
    private void postConstruct() {

        //CacheUtil.setRedisService(this);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 删除指定前缀key
     *
     * @param key 前缀KEY
     * @return
     */
    public void deleteMatch(final String key) {
        Set keys = redisTemplate.keys(key);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }


    public void deleteSamePrefix(final String key) {
        Set keys = redisTemplate.keys(key + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 从右边放入一个数据
     *
     * @param key      缓存的键值
     * @param data 待缓存的data数据
     * @return 缓存的对象
     */
    public <T> long setCacheRightPush(final String key, final T data) {
        Long count = redisTemplate.opsForList().rightPush(key, data);
        return count == null ? 0 : count;
    }

    /**
     * 左边取出数据
     *
     * @param key 缓存的键值
     * @return 缓存的对象
     */
    public Object getListLeftPop(final String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> long setCacheSet(final String key, final Set<T> dataSet) {
        Long count = redisTemplate.opsForSet().add(key, dataSet);
        return count == null ? 0 : count;
    }

    /**
     * 缓存Set单条数据
     *
     * @param key  缓存键值
     * @param data 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> long setCacheSet(final String key, final T data) {
        Long count = redisTemplate.opsForSet().add(key, data);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获取缓存set的大小
     *
     * @param key
     * @return
     */
    public Long getCacheSetSize(final String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * @Description: redis 锁
     * @Param releaseTime
     * @Param key
     * @Param value
     * @Return: boolean
     * @Author: shanghaitao
     * @Date: 2021/3/19 14:58
     */
    public boolean lock(int releaseTime, String key, Object value) {
        //尝试取锁
        Boolean isLock = redisTemplate.opsForValue().setIfAbsent(key + value, value, releaseTime, TimeUnit.SECONDS);
        // 判断结果
        return isLock != null && isLock;
    }

    /**
     * @Description: 释放锁
     * @Param key
     * @Param value
     * @Author: shanghaitao
     * @Date: 2021/3/19 14:59
     */
    public void unlock(String key, Object value) {
        // 删除key即可释放锁
        redisTemplate.delete(key + value);
    }

    /**
     * @Description: 自增
     * @Param key
     * @Param value
     * @Return: java.lang.Long
     * @Author: shanghaitao
     * @Date: 2021/4/20 14:35
     */
    public Long incr(String key, Integer value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    public Long incr(String key, Long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }


    /**
     * @Description: 自减
     * @Param key
     * @Param value
     * @Return: java.lang.Long
     * @Author: shanghaitao
     * @Date: 2021/4/20 14:35
     */
    public Long decr(String key, Integer value) {
        return redisTemplate.opsForValue().decrement(key, value);
    }

    public <T> void setCacheZSet(String key, T data, Double score) {
        redisTemplate.opsForZSet().add(key, data, score);
    }

    public <T> void removeZSetMember(String key, T data) {
        redisTemplate.opsForZSet().remove(key, data);
    }

    /**
     * zSet 添加 set集合
     *
     * @param key
     * @param set
     * @return null
     * @author: ljh
     * @Date: 2021/4/21 17:42
     */
    public <T> void setCacheZSetTypedTuple(String key, Set<ZSetOperations.TypedTuple<T>> set) {
        redisTemplate.opsForZSet().add(key, set);
    }

    /**
     * 获取zSet所有数据
     *
     * @param key 键
     * @author: ljh
     * @Date: 2021/4/21 18:45
     */
    public <T> Set<T> getCacheZSetRange(String key, int page, int size) {
        return redisTemplate.opsForZSet().range(key, page, size);
    }

    public <T> Set<T> getCacheZSetRangeWithScores(String key, int page, int size) {
        return redisTemplate.opsForZSet().rangeWithScores(key, page, size);
    }

    /**
     * 按分数范围删除数据
     *
     * @param var1 key
     * @param var2 0
     * @param var4 100
     * @return 删除行数
     */
    public Long removeRangeByScore(Object var1, double var2, double var4) {
        return redisTemplate.opsForZSet().removeRangeByScore(var1, var2, var4);
    }

    /**
     * setZSetTypedTuple
     *
     * @param key  键
     * @param page
     * @param size
     * @return null
     * @author: ljh
     * @Date: 2021/4/22 18:37
     */
    public <T> Set<ZSetOperations.TypedTuple<T>> getCacheZSetReverseRangeWithScores(String key, int page, int size) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, page, size);
    }

    public <T> Set<T> getCacheZSetReverseRange(String key, int page, int size) {
        return redisTemplate.opsForZSet().reverseRange(key, page, size);
    }

    public <T> Set<T> getCacheZSetReverseRangeByScore(String key, double v1, double v2) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, v1, v2);
    }

    public Long getCacheZSetReverseRank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取zSet的size
     *
     * @param key
     * @author: ljh
     * @Date: 2021/4/22 18:36
     */
    public Long getCacheZSetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取zSet 分数
     *
     * @param key
     * @param value
     * @return
     */
    public <T> Double getCacheZSetScore(String key, T value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * zSet增加score
     *
     * @param key
     * @param value
     * @param score
     */
    public <T> void setCacheZSetIncrementScore(String key, T value, double score) {
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * zSet增加score
     *
     * @param key
     * @param value
     * @param score
     */
    public <T> Double setCacheZSetIncrementScoreVal(String key, T value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * List 更新集合中的某个值
     *
     * @param key
     * @param index
     * @param value
     * @param <T>
     */
    public <T> void setCacheListSingleValue(String key, long index, T value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 判断Key是否存在
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @Description: 获取排行榜最后一名
     * @Param key
     * @Author: shanghaitao
     * @Date: 2021/7/20 17:49
     */
    public <T> Set<ZSetOperations.TypedTuple<T>> getZsetLast(String key) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, -1, -1);
    }

}
