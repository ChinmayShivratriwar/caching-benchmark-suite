package com.chinmayshivratriwar.cache_comparison.service.implementation;

import com.chinmayshivratriwar.cache_comparison.service.CaffeineCacheService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CaffeineCacheServiceImpl implements CaffeineCacheService {
    private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(200_000)
            .build();

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public String get(String key) {
        return cache.getIfPresent(key);
    }
}
