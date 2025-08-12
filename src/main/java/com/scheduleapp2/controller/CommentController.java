package com.scheduleapp2.controller;

import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.comment.CommentResponseDto;
import com.scheduleapp2.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}/users/{userId}")
    public ResponseEntity<CommentResponseDto> createComment(
            @RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto,
            @PathVariable Long scheduleId,
            @PathVariable Long userId) {
        CommentResponseDto createdCommentDto = commentService.createComment(commentCreateRequestDto, scheduleId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentDto);
    }
}
