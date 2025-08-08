package com.scheduleapp2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SCHEDULE_NOT_FOUND("SCH-001", "일정을 찾을 수 없습니다."),

    USER_NOT_FOUND("USR-001", "유저를 찾을 수 없습니다."),
    USER_NOT_LOGIN("USR-002", "로그인이 필요합니다.");

    private final String code;
    private final String message;
}
