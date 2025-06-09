package com.chinmayshivratriwar.cache_comparison.exception;

import lombok.Getter;

@Getter
public class CbsException extends RuntimeException {

    private final String errorCode;

    public CbsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
