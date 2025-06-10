package com.chinmayshivratriwar.cache_comparison.config;

import com.chinmayshivratriwar.cache_comparison.entity.InputObject;
import com.chinmayshivratriwar.cache_comparison.service.CaffeineCacheService;
import com.chinmayshivratriwar.cache_comparison.service.RedisCacheService;
import com.chinmayshivratriwar.cache_comparison.service.implementation.ObjectCaffeineCacheServiceImpl;
import com.chinmayshivratriwar.cache_comparison.service.implementation.ObjectRedisCacheServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class BeanRegistry {

    @Bean(name = "objectRedis")
    public RedisCacheService<String, InputObject> objectRedisCacheService(StringRedisTemplate redisTemplate) {
        return new ObjectRedisCacheServiceImpl<>(redisTemplate, InputObject.class);
    }

    @Bean(name = "objectCaffeine")
    public CaffeineCacheService<String, InputObject> objectCaffeineCacheService() {
        return new ObjectCaffeineCacheServiceImpl<>(InputObject.class);
    }

    // Future registrations of other beans can go here
}
