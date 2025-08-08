package com.scheduleapp2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ErrorResponse(int status, String errorCode, String message, String path,
                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp) {
    public static ErrorResponse of(HttpStatus status, String errorCode, String message, String path) {
        return new ErrorResponse(status.value(), errorCode, message, path, LocalDateTime.now());
    }
}