package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.schedule.ScheduleRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule toEntity(ScheduleRequestDto scheduleRequestDto);
    ScheduleResponseDto toResponseDto(Schedule schedule);
}