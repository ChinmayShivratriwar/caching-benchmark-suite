package com.chinmayshivratriwar.cache_comparison.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface CaffeineCacheService<K, V> {
    void put(K key, V value);

    V get(K key) throws JsonProcessingException;
}
