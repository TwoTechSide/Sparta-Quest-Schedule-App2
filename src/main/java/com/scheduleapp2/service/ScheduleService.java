package com.scheduleapp2.service;

import com.scheduleapp2.dto.schedule.ScheduleCreateRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleListResponseDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.dto.schedule.ScheduleUpdateRequestDto;
import com.scheduleapp2.entity.Schedule;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.common.exception.BusinessException;
import com.scheduleapp2.common.exception.ErrorCode;
import com.scheduleapp2.mapper.ScheduleMapper;
import com.scheduleapp2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService  {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto scheduleCreateRequestDto, User user) {

        Schedule schedule = scheduleMapper.toEntityWithUser(scheduleCreateRequestDto, user);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toResponseDto(savedSchedule);
    }

    public ScheduleListResponseDto findAllSchedules() {
        List<Schedule> foundSchedules = scheduleRepository.findAll();
        return new ScheduleListResponseDto(scheduleMapper.toListResponseDto(foundSchedules));
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto, Long scheduleId) {
        Schedule schedule = findScheduleByIdOrElseThrow(scheduleId);

        schedule.updateTitleAndContent(scheduleUpdateRequestDto);
        scheduleRepository.saveAndFlush(schedule);

        return scheduleMapper.toResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public ScheduleListResponseDto getSchedulePage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedulePage = scheduleRepository.findAllByOrderByUpdatedAtDesc(pageable);

        return new ScheduleListResponseDto(scheduleMapper.toListResponseDto(schedulePage));
    }

    // findById로 찾은 entity 반환, 조회 실패시 예외 처리
    public Schedule findScheduleByIdOrElseThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}