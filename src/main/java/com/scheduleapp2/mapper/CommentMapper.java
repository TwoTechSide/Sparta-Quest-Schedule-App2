package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.comment.CommentResponseDto;
import com.scheduleapp2.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentCreateRequestDto commentCreateRequestDto);
    CommentResponseDto toResponseDto(Comment comment);
}
