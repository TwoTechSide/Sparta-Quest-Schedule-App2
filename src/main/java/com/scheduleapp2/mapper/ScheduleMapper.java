package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.schedule.ScheduleCreateRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule toEntity(ScheduleCreateRequestDto scheduleCreateRequestDto);
    ScheduleResponseDto toResponseDto(Schedule schedule);
}