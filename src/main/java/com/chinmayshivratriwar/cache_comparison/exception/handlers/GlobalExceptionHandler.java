package com.chinmayshivratriwar.cache_comparison.exception.handlers;

import com.chinmayshivratriwar.cache_comparison.constants.CbsConstants;
import com.chinmayshivratriwar.cache_comparison.entity.ErrorResponse;
import com.chinmayshivratriwar.cache_comparison.exception.CbsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CbsException.class)
    public ResponseEntity<ErrorResponse> handleCbsException(CbsException exception) {
        log.error(CbsConstants.CBS_ERROR_EXPRESSION, exception.getErrorCode(), exception.getMessage());
        return ResponseEntity.internalServerError().body(
                ErrorResponse.builder()
                        .errDescription(exception.getMessage())
                        .errorCode(exception.getErrorCode())
                        .localDateTime(LocalDateTime.now().toString())
                        .build()
        );
    }
}
