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

    // Service 에서 발생한 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e, HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.of(e.getHttpStatus(), e.getCode(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, e.getHttpStatus());
    }

    // Controller 에서 발생항 @Valid 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.of(HttpStatus.BAD_REQUEST, "VAL-001", e.getAllErrors().get(0).getDefaultMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
