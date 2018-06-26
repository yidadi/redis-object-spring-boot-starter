package com.haixue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.haixue.serializer.FastJsonRedisSerializer;
import com.haixue.serializer.KryoRedisSerializer;


@Configuration
@EnableConfigurationProperties(RedisSerializerProperties.class)
public class RedisSerializerConfiguration {
    @Autowired
    private RedisSerializerProperties redisSerializerProperties;

    /**
     *
     * @param redisTemplate
     * @return
     */
    @Bean("redisTemplate")
    @ConditionalOnProperty(prefix = "haixue.redis", name = "type", havingValue = "json")
    public RedisTemplate redisTemplateJson(RedisTemplate redisTemplate) {
        // 全局开启AutoType，不建议使用
        // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept(redisSerializerProperties.getWhiteListPackage());
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     *
     * @param redisTemplate
     * @return
     */
    @Bean("redisTemplate")
    @ConditionalOnProperty(prefix = "haixue.redis", name = "type", havingValue = "kryo")
    public RedisTemplate redisTemplateKryo(RedisTemplate redisTemplate) {
        redisTemplate.setValueSerializer(new KryoRedisSerializer<>(Object.class));
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
