package com.chinmayshivratriwar.cache_comparison.service.implementation;

import com.chinmayshivratriwar.cache_comparison.constants.CbsConstants;
import com.chinmayshivratriwar.cache_comparison.service.RedisCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

// @Service is not used here since we are doing bean registry for class<T> manually
// @Service cannot handle automatic bean registry for Class<T>
@Slf4j
public class ObjectRedisCacheServiceImpl<T> implements RedisCacheService<String, T> {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    public ObjectRedisCacheServiceImpl(StringRedisTemplate redisTemplate, Class<T> type) {
        this.redisTemplate = redisTemplate;
        this.type = type;
    }


    @Override
    public void put(String key, T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object", e);
        }
    }

    @Override
    public T get(String key) throws JsonProcessingException {
        String json = redisTemplate.opsForValue().get(key);
        try {
            return json == null ? null : objectMapper.readValue(json, type);
        } catch (JsonProcessingException exception) {
            log.warn(CbsConstants.CBS_WARN_EXCEPTION_EXPRESSION, "ObjectRedisCacheServiceImpl");
            throw exception;
        }
    }
}
