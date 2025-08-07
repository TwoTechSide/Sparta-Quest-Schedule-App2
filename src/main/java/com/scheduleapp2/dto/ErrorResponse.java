package com.scheduleapp2.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private int status;

    private String errorCode;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public static ErrorResponse of(HttpStatus status, String errorCode, String message, String path) {
        return new ErrorResponse(status.value(), errorCode, message, path, LocalDateTime.now());
    }
}
