package com.scheduleapp2.mapper;

import com.scheduleapp2.dto.user.UserResponseDto;
import com.scheduleapp2.dto.user.UserSignupRequestDto;
import com.scheduleapp2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "encodedPassword", target = "password")
    User toEntityWithEncodedPassword(UserSignupRequestDto userSignupRequestDto, String encodedPassword);
    UserResponseDto toResponseDto(User user);
}
