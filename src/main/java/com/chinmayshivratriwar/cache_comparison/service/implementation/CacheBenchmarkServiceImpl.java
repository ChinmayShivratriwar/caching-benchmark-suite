package com.chinmayshivratriwar.cache_comparison.service.implementation;

import com.chinmayshivratriwar.cache_comparison.enums.ErrorType;
import com.chinmayshivratriwar.cache_comparison.exception.CbsException;
import com.chinmayshivratriwar.cache_comparison.service.CacheBenchmarkService;
import com.chinmayshivratriwar.cache_comparison.service.CaffeineCacheService;
import com.chinmayshivratriwar.cache_comparison.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheBenchmarkServiceImpl implements CacheBenchmarkService {

    private final CaffeineCacheService caffeineCacheService;
    private final RedisCacheService redisCacheService;

    @Override
    public Map<String, String> getBenchmarkResponses(int testSize) {
        Map<String, String> benchmarkResponse = new HashMap<>();
        try {
            log.info("--- Caffeine Benchmark ---");
            long start = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {
                caffeineCacheService.put("key" + i, "value" + i);
            }
            for (int i = 0; i < testSize; i++) {
                caffeineCacheService.get("key" + i);
            }
            long end = System.currentTimeMillis();
            log.info("Caffeine Total Time: {} ms", (end - start));
            benchmarkResponse.put("caffeine", String.valueOf((end - start)));

            log.info("--- Redis Benchmark ---");
            start = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {
                redisCacheService.put("key" + i, "value" + i);
            }
            for (int i = 0; i < testSize; i++) {
                redisCacheService.get("key" + i);
            }
            end = System.currentTimeMillis();
            log.info("Redis Total Time: {} ms", (end - start));
            benchmarkResponse.put("redis", String.valueOf((end - start)));
        } catch (Exception exception) {
            throw new CbsException(ErrorType.VANILLA_FLOW_EXCEPTION.getCode(), exception.getMessage());
        }
        return benchmarkResponse;
    }
}
