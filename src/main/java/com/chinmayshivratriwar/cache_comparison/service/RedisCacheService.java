package com.chinmayshivratriwar.cache_comparison.service;

import org.springframework.stereotype.Service;

@Service
public interface RedisCacheService {
    void put(String key, String value);
    String get(String key);
}
