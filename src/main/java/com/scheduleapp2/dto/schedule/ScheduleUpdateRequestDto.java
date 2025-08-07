package com.scheduleapp2.dto.schedule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleUpdateRequestDto {

    private final String title;
    private final String content;
}