package com.scheduleapp2.controller;

import com.scheduleapp2.dto.schedule.ScheduleRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.dto.schedule.ScheduleUpdateRequestDto;
import com.scheduleapp2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/users/{userId}")
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody ScheduleRequestDto scheduleRequestDto,
            @PathVariable Long userId) {
        ScheduleResponseDto createdScheduleDto = scheduleService.createSchedule(scheduleRequestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScheduleDto);
    }

    // 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        List<ScheduleResponseDto> allScheduleDto = scheduleService.findAllSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(allScheduleDto);
    }

    // 일정 수정
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto,
            @PathVariable Long scheduleId) {
        ScheduleResponseDto updatedScheduleDto = scheduleService.updateSchedule(scheduleUpdateRequestDto, scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedScheduleDto);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
