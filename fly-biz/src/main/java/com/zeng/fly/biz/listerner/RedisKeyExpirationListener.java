package com.zeng.fly.biz.listerner;

import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * key过期监听
 *
 * @author zengxingdeng
 * @data 2022/4/6
 * @des
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 指定redis库
     * @param listenerContainer
     */
    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        listenerContainer.addMessageListener(this, new PatternTopic("__keyevent@" + 15 + "__:expired"));
    }

    /**
     * key失效回调消息，可能出现重复
     * @param message
     * @param pattern
     */
    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 获取过期的key
        String expireKey = message.toString();
        System.out.println("终于失效了" + new Date());
        Thread.sleep(1000);
        System.out.println("key is:" + expireKey);
    }
}
