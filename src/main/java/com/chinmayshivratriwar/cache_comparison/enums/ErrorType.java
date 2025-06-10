package com.chinmayshivratriwar.cache_comparison.enums;

import lombok.Getter;

@Getter
public enum ErrorType {

    VANILLA_FLOW_EXCEPTION("CNS_CBS_E0001", "Error while computing in vanilla flow"),
    BLOB_FLOW_EXCEPTION("CNS_CBS_E0002", "Error while computing in blob flow"),
    JSON_PROCESSING_EXCEPTION("CNS_CBS_E0003", "Error while serialize / deserialize ");

    private final String code;
    private final String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
