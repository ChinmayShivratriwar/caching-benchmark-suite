package com.chinmayshivratriwar.cache_comparison.util;

import com.chinmayshivratriwar.cache_comparison.entity.InputObject;

import java.time.LocalDateTime;
import java.util.UUID;

public class BlobEnricher {

    private BlobEnricher() {

    }

    public static InputObject generateBlob(int sizeInKb) {
        byte[] generatedPayload = new byte[sizeInKb * 1024]; // 198 KB payload (all zeros)
        return InputObject.builder()
                .type(UUID.randomUUID().toString())
                .correlationId(UUID.randomUUID().toString())
                .payload(generatedPayload)
                .region(UUID.randomUUID().toString())
                .generationDateTime(LocalDateTime.now().toString())
                .build();
    }
}
