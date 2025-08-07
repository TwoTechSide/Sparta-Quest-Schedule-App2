package com.scheduleapp2.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleUpdateRequestDto {

    private String title;
    private String content;
}