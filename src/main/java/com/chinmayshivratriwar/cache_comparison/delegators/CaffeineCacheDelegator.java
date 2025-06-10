package com.chinmayshivratriwar.cache_comparison.delegators;

import com.chinmayshivratriwar.cache_comparison.entity.InputObject;
import com.chinmayshivratriwar.cache_comparison.service.CaffeineCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CaffeineCacheDelegator {

    private final CaffeineCacheService<String, String> stringService;
    private final CaffeineCacheService<String, InputObject> objectService;

    public CaffeineCacheDelegator(
            @Qualifier("stringCaffeine") CaffeineCacheService<String, String> stringService,
            @Qualifier("objectCaffeine") CaffeineCacheService<String, InputObject> objectService
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

