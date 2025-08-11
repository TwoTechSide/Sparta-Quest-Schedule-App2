package com.scheduleapp2.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserSignupRequestDto(
        // 닉네임 유효성 검사
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        String name,

        // 이메일 유효성 검사
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
        String email,

        // 비밀번호 유효성 검사 (4~16자, 대문자 또는 소문자 1개 이상 반드시 포함, 특수문자 1개 이상 반드시 포함, 숫자 1개 이상 반드시 포함)
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*?&#.~_-])[A-Za-z0-9@$!%*?&#.~_-]{4,16}$", message = "비밀번호는 4~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.")
        String password) {

}