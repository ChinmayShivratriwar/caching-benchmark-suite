package com.chinmayshivratriwar.cache_comparison.service.implementation;

import com.chinmayshivratriwar.cache_comparison.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("stringRedis")
@RequiredArgsConstructor
public class StringRedisCacheServiceImpl implements RedisCacheService<String, String> {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
