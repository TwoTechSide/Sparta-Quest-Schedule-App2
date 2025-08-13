package com.scheduleapp2.dto.comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
