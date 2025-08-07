package com.scheduleapp2.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleRequestDto {

    private final String writer;
    private final String title;
    private final String content;
}