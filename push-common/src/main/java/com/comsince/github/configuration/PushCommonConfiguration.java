package com.comsince.github.configuration;

import com.comsince.github.MessageService;
import com.comsince.github.SessionService;
import com.comsince.github.sub.SubService;
import io.netty.util.internal.StringUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * @author comsicne
 *         Copyright (c) [2019] [Meizu.inc]
 * @Time 19-2-28 下午12:36
 **/

public class PushCommonConfiguration {

    Logger logger = LoggerFactory.getLogger(PushCommonConfiguration.class);

    @Autowired
    RedisProperties redisProperties;

    @Autowired
    KafkaProperties kafkaProperties;

    @Reference
    private SubService subService;

    @Reference
    private MessageService messageService;

    @Reference
    private SessionService sessionService;

    @Bean(destroyMethod="shutdown")
    RedissonClient redissonClient() throws IOException {
        RedissonClient redissonClient = null;
        if(!StringUtil.isNullOrEmpty(redisProperties.getAddress())){
            Config config = new Config();
            config.useSingleServer().setAddress(redisProperties.getAddress()).setPassword(redisProperties.getPassword());
            redissonClient = Redisson.create(config);
            logger.info("create redisson client successful");
        }

        return redissonClient;
    }

    public SubService subService(){
        return subService;
    }

    public MessageService messageService(){
        return messageService;
    }

    public SessionService sessionService(){
        return sessionService;
    }
}
