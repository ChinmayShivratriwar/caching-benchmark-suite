package com.chinmayshivratriwar.cache_comparison.controller;

import com.chinmayshivratriwar.cache_comparison.service.CacheBenchmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/cbs")
@RequiredArgsConstructor
public class ComparisonController {

    private final CacheBenchmarkService cacheBenchmarkService;

    @PostMapping("/redis-v-caffine")
    public ResponseEntity<Map<String, String>> getVanillaBenchmarkingData(
            @RequestParam(defaultValue = "5000") int testSize
    ) {
        Map<String, String> result = cacheBenchmarkService.getBenchmarkResponses(testSize);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/rc-blob-flow")
    public ResponseEntity<Map<String, String>> getBlobBenchmarkingData(
            @RequestParam(defaultValue = "5000") int testSize,
            @RequestParam(defaultValue = "100") int blobSizeInKb
    ) {
        Map<String, String> result = cacheBenchmarkService.getBenchmarkResponsesForObjects(testSize, blobSizeInKb);
        return ResponseEntity.ok(result);
    }
}
