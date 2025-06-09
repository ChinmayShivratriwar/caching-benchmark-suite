package com.chinmayshivratriwar.cache_comparison.controller;

import com.chinmayshivratriwar.cache_comparison.service.CacheBenchmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
