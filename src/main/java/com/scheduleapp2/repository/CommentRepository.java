package com.scheduleapp2.repository;

import com.scheduleapp2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByScheduleId(Long scheduleId);
    List<Comment> findAllByUserId(Long userId);
}