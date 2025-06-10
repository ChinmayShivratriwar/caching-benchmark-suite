package com.chinmayshivratriwar.cache_comparison.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CacheBenchmarkService {
    Map<String, String> getBenchmarkResponses(int testSize);

    Map<String, String> getBenchmarkResponsesForObjects(int testSize, int blobSizeInKb);
}
