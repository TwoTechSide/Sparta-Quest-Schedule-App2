package com.scheduleapp2.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleRequestDto {

    private String writer;
    private String title;
    private String content;
}