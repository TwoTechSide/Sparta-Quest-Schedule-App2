package com.scheduleapp2.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScheduleCreateRequestDto(
        @NotBlank(message = "작성자명은 필수 입력 값입니다.")
        String writer,
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @Size(min = 2, max = 40)
        String title,
        @NotBlank(message = "내용은 필수 입력 값입니다.")
        @Size(min = 2, max = 200)
        String content) { }