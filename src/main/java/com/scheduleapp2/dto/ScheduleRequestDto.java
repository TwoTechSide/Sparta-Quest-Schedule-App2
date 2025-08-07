package com.scheduleapp2.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private String writer;
    private String title;
    private String content;
}