package com.zeng.fly.common.redis;

import com.zeng.fly.common.redis.template.TryLockTemplate;
import com.zeng.fly.common.redis.template.TryLockTemplateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @title: RedisConfig
 * @Author: wangxiaodong
 * @Date: 2020/11/11 9:37
 * @Version 1.0
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * DefaultListableBeanFactory，是 GenericApplicationContext 和 AbstractRefreshableApplicationContext 的(bean工厂)默认策略，
     * 而这两者是所有 xxxApplicationContext 的基类，因此 DefaultListableBeanFactory 可以说是 ApplicationContext 的标准配置
     */
    private DefaultListableBeanFactory beanFactory;

    private RedisConnectionFactory redisConnectionFactory;

    @PostConstruct
    public void postConstruct() {
        logger.info("yzsy-redis-boot-starter started...");
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 配置序列化
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = config
                //默认7天
                .entryTtl(Duration.ofDays(7))
                // 键序列化方式 redis字符串序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                // 值序列化方式 简单json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    //    private RedisSerializer<Object> valueSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }
    private RedisSerializer<Object> valueSerializer() {
        return new GenericMsgpackRedisSerializer();
    }


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

    @Bean
    public KeyExpirationEventMessageListener keyExpirationEventMessageListener(RedisConnectionFactory redisConnectionFactory) {
        return new KeyExpirationEventMessageListener(this.redisMessageListenerContainer(redisConnectionFactory));
    }

    @Bean
    public TryLockTemplate tryLockTemplate() {
        return new TryLockTemplateImpl();
    }


}
