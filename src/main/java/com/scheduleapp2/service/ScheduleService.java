package com.scheduleapp2.service;

import com.scheduleapp2.dto.ScheduleRequestDto;
import com.scheduleapp2.dto.ScheduleResponseDto;
import com.scheduleapp2.entity.Schedule;
import com.scheduleapp2.mapper.ScheduleMapper;
import com.scheduleapp2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService  {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.save(scheduleMapper.toEntity(scheduleRequestDto));
        return scheduleMapper.toResponseDto(schedule);
    }
}