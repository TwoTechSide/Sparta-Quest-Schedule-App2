package com.scheduleapp2.controller;

import com.scheduleapp2.common.annotation.LoginUserResolver;
import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.comment.CommentListResponseDto;
import com.scheduleapp2.dto.comment.CommentResponseDto;
import com.scheduleapp2.dto.comment.CommentUpdateRequestDto;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}")
    public ResponseEntity<CommentResponseDto> createComment(
            @RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto,
            @PathVariable Long scheduleId,
            @LoginUserResolver User user) {

        CommentResponseDto createdCommentDto = commentService.createComment(commentCreateRequestDto, scheduleId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentDto);
    }

    // 댓글 불러오기 (ScheduleId)
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<CommentListResponseDto> getAllCommentsFromSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsByScheduleId(scheduleId));
    }

    // 댓글 불러오기 (UserId)
    @GetMapping("/users/{userId}")
    public ResponseEntity<CommentListResponseDto> getAllCommentsFromUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsByUserId(userId));
    }

    // 댓글 수정하기
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @RequestBody CommentUpdateRequestDto commentUpdateRequestDto,
            @PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentUpdateRequestDto, commentId));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
