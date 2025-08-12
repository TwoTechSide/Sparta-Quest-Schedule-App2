package com.scheduleapp2.service;

import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.comment.CommentResponseDto;
import com.scheduleapp2.entity.Comment;
import com.scheduleapp2.entity.Schedule;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.exception.CustomException;
import com.scheduleapp2.exception.ErrorCode;
import com.scheduleapp2.mapper.CommentMapper;
import com.scheduleapp2.repository.CommentRepository;
import com.scheduleapp2.repository.ScheduleRepository;
import com.scheduleapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto,
                                            Long scheduleId,
                                            Long userId) {

        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        Optional<User> user = userRepository.findById(userId);

        if (schedule.isEmpty()) {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        } else if (user.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        Comment comment = commentMapper.toEntity(commentCreateRequestDto);
        comment.withUserAndSchedule(user.get(), schedule.get());
        try {
            return commentMapper.toResponseDto(commentRepository.save(comment));
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorCode.COMMENT_NOT_CREATED);
        }
    }
}
