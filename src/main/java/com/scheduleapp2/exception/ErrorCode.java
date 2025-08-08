package com.scheduleapp2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SCHEDULE_NOT_FOUND("SCH-001", "일정을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND("USR-001", "유저를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_LOGIN("USR-002", "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    USER_LOGIN_FAIL("USR-003", "로그인에 실패하였습니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
