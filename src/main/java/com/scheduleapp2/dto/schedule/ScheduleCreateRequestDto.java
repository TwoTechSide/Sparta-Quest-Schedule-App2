package com.scheduleapp2.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScheduleCreateRequestDto(
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @Size(min = 2, max = 40, message = "제목은 2글자 이상 40글자 이하로 작성해야 합니다.")
        String title,
        @NotBlank(message = "내용은 필수 입력 값입니다.")
        @Size(min = 2, max = 200, message = "제목은 2글자 이상 200글자 이하로 작성해야 합니다.")
        String content) {

}