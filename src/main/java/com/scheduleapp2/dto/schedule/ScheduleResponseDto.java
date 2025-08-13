package com.scheduleapp2.dto.schedule;

import java.time.LocalDateTime;

public record ScheduleResponseDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}