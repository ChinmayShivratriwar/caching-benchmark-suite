package com.chinmayshivratriwar.cache_comparison.service.implementation;

import com.chinmayshivratriwar.cache_comparison.constants.CbsConstants;
import com.chinmayshivratriwar.cache_comparison.service.CaffeineCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

// @Service is not used here since we are doing bean registry for class<T> manually
// @Service cannot handle automatic bean registry for Class<T>
@Slf4j
public class ObjectCaffeineCacheServiceImpl<T> implements CaffeineCacheService<String, T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    public ObjectCaffeineCacheServiceImpl(Class<T> type) {
        this.type = type;
    }

    private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(200_000)
            .build();

    @Override
    public void put(String key, T obj) {
        try {
            cache.put(key, objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T get(String key) throws JsonProcessingException {
        String json = cache.getIfPresent(key);
        try {
            return json == null ? null : objectMapper.readValue(json, type);
        } catch (JsonProcessingException exception) {
            log.warn(CbsConstants.CBS_WARN_EXCEPTION_EXPRESSION, "ObjectCaffeineCacheServiceImpl");
            throw exception;
        }
    }
}
