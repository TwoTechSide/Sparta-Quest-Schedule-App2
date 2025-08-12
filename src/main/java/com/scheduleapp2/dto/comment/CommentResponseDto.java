package com.scheduleapp2.dto.comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
