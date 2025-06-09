package com.chinmayshivratriwar.cache_comparison.service.implementation;
import com.chinmayshivratriwar.cache_comparison.service.CacheBenchmarkService;
import com.chinmayshivratriwar.cache_comparison.service.CaffeineCacheService;
import com.chinmayshivratriwar.cache_comparison.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheBenchmarkServiceImpl implements CacheBenchmarkService {

    private final CaffeineCacheService caffeineCacheService;
    private final RedisCacheService redisCacheService;
    private final int TEST_SIZE = 5_000;

    @Override
    public void runTests() {
        System.out.println("--- Caffeine Benchmark ---");
        long start = System.currentTimeMillis();
        for (int i = 0; i < TEST_SIZE; i++) {
            caffeineCacheService.put("key" + i, "value" + i);
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            caffeineCacheService.get("key" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Caffeine Total Time: " + (end - start) + "ms");

        System.out.println("--- Redis Benchmark ---");
        start = System.currentTimeMillis();
        for (int i = 0; i < TEST_SIZE; i++) {
            redisCacheService.put("key" + i, "value" + i);
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            redisCacheService.get("key" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Redis Total Time: " + (end - start) + "ms");
    }
}
