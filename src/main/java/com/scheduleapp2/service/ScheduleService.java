package com.scheduleapp2.service;

import com.scheduleapp2.dto.schedule.ScheduleRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.dto.schedule.ScheduleUpdateRequestDto;
import com.scheduleapp2.entity.Schedule;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.exception.CustomException;
import com.scheduleapp2.exception.ErrorCode;
import com.scheduleapp2.mapper.ScheduleMapper;
import com.scheduleapp2.repository.ScheduleRepository;
import com.scheduleapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService  {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    private final ScheduleMapper scheduleMapper;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Schedule scheduleWithUser = scheduleMapper.toEntity(scheduleRequestDto);
        scheduleWithUser.assignUser(user);

        Schedule savedSchedule = scheduleRepository.save(scheduleWithUser);
        return scheduleMapper.toResponseDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedules() {
        List<Schedule> foundSchedules = scheduleRepository.findAll();
        return foundSchedules.stream().map(scheduleMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto, Long scheduleId) {
        Schedule schedule = findScheduleByIdOrThrow(scheduleId);

        schedule.updateTitleAndContent(scheduleUpdateRequestDto);
        scheduleRepository.saveAndFlush(schedule);

        return scheduleMapper.toResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    // findById로 찾은 entity 반환, 조회 실패시 예외 처리
    public Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}