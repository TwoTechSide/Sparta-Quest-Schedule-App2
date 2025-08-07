package com.scheduleapp2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String writer;
    private final String title;
    private final String content;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public ScheduleResponseDto(Long id, String writer, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}