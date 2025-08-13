package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.schedule.ScheduleCreateRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.entity.Schedule;
import com.scheduleapp2.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "loginUser", target = "user")
    Schedule toEntityWithUser(ScheduleCreateRequestDto scheduleCreateRequestDto, User loginUser);

    @Named("toResponseDto(Schedule)")
    ScheduleResponseDto toResponseDto(Schedule schedule);

    @IterableMapping(qualifiedByName = "toResponseDto(Schedule)")
    List<ScheduleResponseDto> toListResponseDto(List<Schedule> schedules);

    @IterableMapping(qualifiedByName = "toResponseDto(Schedule)")
    List<ScheduleResponseDto> toListResponseDto(Page<Schedule> schedules);
}