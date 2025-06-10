package com.chinmayshivratriwar.cache_comparison.delegators;

import com.chinmayshivratriwar.cache_comparison.entity.InputObject;
import com.chinmayshivratriwar.cache_comparison.service.RedisCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheDelegator {

    private final RedisCacheService<String, String> stringService;
    private final RedisCacheService<String, InputObject> objectService;

    public RedisCacheDelegator(
            @Qualifier("stringRedis") RedisCacheService<String, String> stringService,
            @Qualifier("objectRedis") RedisCacheService<String, InputObject> objectService
    ) {
        this.stringService = stringService;
        this.objectService = objectService;
    }

    public void putString(String key, String value) {
        stringService.put(key, value);
    }

    public String getString(String key) throws JsonProcessingException {
        return stringService.get(key);
    }

    public void putObject(String key, InputObject value) {
        objectService.put(key, value);
    }

    public InputObject getObject(String key) throws JsonProcessingException {
        return objectService.get(key);
    }
}

