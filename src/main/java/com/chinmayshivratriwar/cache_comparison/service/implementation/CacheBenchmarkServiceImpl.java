package com.chinmayshivratriwar.cache_comparison.service.implementation;

import com.chinmayshivratriwar.cache_comparison.delegators.CaffeineCacheDelegator;
import com.chinmayshivratriwar.cache_comparison.delegators.RedisCacheDelegator;
import com.chinmayshivratriwar.cache_comparison.enums.ErrorType;
import com.chinmayshivratriwar.cache_comparison.exception.CbsException;
import com.chinmayshivratriwar.cache_comparison.service.CacheBenchmarkService;
import com.chinmayshivratriwar.cache_comparison.util.BlobEnricher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheBenchmarkServiceImpl implements CacheBenchmarkService {

    private final CaffeineCacheDelegator caffeineCacheDelegator;
    private final RedisCacheDelegator redisCacheDelegator;

    @Override
    public Map<String, String> getBenchmarkResponses(int testSize) {
        Map<String, String> benchmarkResponse = new HashMap<>();
        try {
            log.info("--- Caffeine Benchmark ---");
            long start = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {
                caffeineCacheDelegator.putString("key" + i, "value" + i);
            }
            for (int i = 0; i < testSize; i++) {
                caffeineCacheDelegator.getString("key" + i);
            }
            long end = System.currentTimeMillis();
            log.info("Caffeine Total Time: {} ms", (end - start));
            benchmarkResponse.put("caffeine", String.valueOf((end - start)));

            log.info("--- Redis Benchmark ---");
            start = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {
                redisCacheDelegator.putString("key" + i, "value" + i);
            }
            for (int i = 0; i < testSize; i++) {
                redisCacheDelegator.getString("key" + i);
            }
            end = System.currentTimeMillis();
            log.info("Redis Total Time: {} ms", (end - start));
            benchmarkResponse.put("redis", String.valueOf((end - start)));
        } catch (Exception exception) {
            throw new CbsException(ErrorType.VANILLA_FLOW_EXCEPTION.getCode(), exception.getMessage());
        }
        return benchmarkResponse;
    }

    @Override
    public Map<String, String> getBenchmarkResponsesForObjects(int testSize, int blobSizeInKb) {
        Map<String, String> benchmarkResponse = new HashMap<>();
        try {
            log.info("--- Caffeine Benchmark ---");
            long start = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {
                caffeineCacheDelegator.putObject("blob-key" + i, BlobEnricher.generateBlob(blobSizeInKb));
            }
            for (int i = 0; i < testSize; i++) {
                caffeineCacheDelegator.getObject("blob-key" + i);
            }
            long end = System.currentTimeMillis();
            log.info("Blob Flow - Caffeine Total Time: {} ms", (end - start));
            benchmarkResponse.put("caffeine", String.valueOf((end - start)));

            log.info("--- Redis Benchmark ---");
            start = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {
                redisCacheDelegator.putObject("blob-key" + i, BlobEnricher.generateBlob(blobSizeInKb));
            }
            for (int i = 0; i < testSize; i++) {
                redisCacheDelegator.getObject("blob-key" + i);
            }
            end = System.currentTimeMillis();
            log.info("Blob Flow - Redis Total Time: {} ms", (end - start));
            benchmarkResponse.put("redis", String.valueOf((end - start)));
            return benchmarkResponse;
        } catch (Exception exception) {
            throw new CbsException(ErrorType.BLOB_FLOW_EXCEPTION.getCode(), exception.getMessage());
        }
    }
}
