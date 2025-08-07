package com.scheduleapp2.service;

import com.scheduleapp2.dto.ScheduleRequestDto;
import com.scheduleapp2.dto.ScheduleResponseDto;
import com.scheduleapp2.dto.ScheduleUpdateRequestDto;
import com.scheduleapp2.entity.Schedule;
import com.scheduleapp2.mapper.ScheduleMapper;
import com.scheduleapp2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService  {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.save(scheduleMapper.toEntity(scheduleRequestDto));
        return scheduleMapper.toResponseDto(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedules() {
        List<Schedule> foundSchedules = scheduleRepository.findAll();
        return foundSchedules.stream().map(scheduleMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();

        schedule.updateTitleAndContent(scheduleUpdateRequestDto);
        scheduleRepository.saveAndFlush(schedule);

        return scheduleMapper.toResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}