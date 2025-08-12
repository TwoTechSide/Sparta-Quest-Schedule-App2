package com.scheduleapp2.service;

import com.scheduleapp2.config.PasswordEncoder;
import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.comment.CommentResponseDto;
import com.scheduleapp2.dto.comment.CommentUpdateRequestDto;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PasswordEncoder pwEncoder;

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
        comment.assignUserAndSchedule(user.get(), schedule.get());
        try {
            return commentMapper.toResponseDto(commentRepository.save(comment));
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorCode.COMMENT_NOT_CREATED);
        }
    }

    public List<CommentResponseDto> getAllCommentsByScheduleId(Long scheduleId) {
        List<Comment> comments = commentRepository.findAllByScheduleId(scheduleId);
        return comments.stream().map(commentMapper::toResponseDto).collect(Collectors.toList());
    }

    public List<CommentResponseDto> getAllCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.findAllByUserId(userId);
        return comments.stream().map(commentMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto commentUpdateRequestDto, Long commentId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isEmpty())
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);

        Comment comment = optionalComment.get();
        if (pwEncoder.matches(commentUpdateRequestDto.userPassword(), comment.getUser().getPassword())) {
            comment.updateContent(commentUpdateRequestDto.content());
            return commentMapper.toResponseDto(commentRepository.saveAndFlush(comment));
        } else {
            throw new CustomException(ErrorCode.USER_PASSWORD_INCORRECT);
        }
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
