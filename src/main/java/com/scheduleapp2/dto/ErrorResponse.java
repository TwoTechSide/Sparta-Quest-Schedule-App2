package com.scheduleapp2.dto;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ErrorResponse(int status, String errorCode, String message, String path, LocalDateTime timestamp) {
    public static ErrorResponse of(HttpStatus status, String errorCode, String message, String path) {
        return new ErrorResponse(status.value(), errorCode, message, path, LocalDateTime.now());
    }
}