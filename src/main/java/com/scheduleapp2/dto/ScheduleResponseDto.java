package com.scheduleapp2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleResponseDto {

    private Long id;

    private String writer;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}