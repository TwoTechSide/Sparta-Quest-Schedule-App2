package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.comment.CommentResponseDto;
import com.scheduleapp2.entity.Comment;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentCreateRequestDto commentCreateRequestDto);

    @Named("toResponseDto(Comment)")
    CommentResponseDto toResponseDto(Comment comment);

    @IterableMapping(qualifiedByName = "toResponseDto(Comment)")
    List<CommentResponseDto> toResponseDto(List<Comment> comments);
}
