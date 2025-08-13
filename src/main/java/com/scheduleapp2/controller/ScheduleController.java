package com.scheduleapp2.controller;

import com.scheduleapp2.dto.schedule.ScheduleCreateRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleListResponseDto;
import com.scheduleapp2.dto.schedule.ScheduleResponseDto;
import com.scheduleapp2.dto.schedule.ScheduleUpdateRequestDto;
import com.scheduleapp2.service.ScheduleService;
import jakarta.validation.Valid;
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
            @RequestBody @Valid ScheduleCreateRequestDto scheduleCreateRequestDto,
            @PathVariable Long userId) {
        ScheduleResponseDto createdScheduleDto = scheduleService.createSchedule(scheduleCreateRequestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScheduleDto);
    }

    // 일정 조회
    @GetMapping
    public ResponseEntity<ScheduleListResponseDto> getAllSchedules() {
        ScheduleListResponseDto allScheduleList = scheduleService.findAllSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(allScheduleList);
    }

    // 일정 페이지 조회
    @GetMapping("/page")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulePage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedulePage(page, size));
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
