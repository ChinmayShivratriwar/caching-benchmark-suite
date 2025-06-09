package com.chinmayshivratriwar.cache_comparison.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ErrorResponse {
    String errorCode;
    String errDescription;
    String localDateTime;
}
