package com.scheduleapp2.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String name;
    private final String email;

    @Builder
    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
