package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.user.UserRequestDto;
import com.scheduleapp2.dto.user.UserResponseDto;
import com.scheduleapp2.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto userRequestDto);
    UserResponseDto toResponseDto(User user);
}
