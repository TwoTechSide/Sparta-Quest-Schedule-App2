package com.scheduleapp2.dto.user;

import java.time.LocalDateTime;

public record UserResponseDto(
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}