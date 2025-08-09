package com.scheduleapp2.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRequestDto(
        // 닉네임 유효성 검사
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        String name,

        // 이메일 유효성 검사
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
        String email) {

}