package com.scheduleapp2.exception;

import com.scheduleapp2.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e, HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.of(e.getHttpStatus(), e.getCode(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, e.getHttpStatus());
    }
}
