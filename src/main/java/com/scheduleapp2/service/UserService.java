package com.scheduleapp2.service;

import com.scheduleapp2.dto.user.UserLoginRequestDto;
import com.scheduleapp2.dto.user.UserRequestDto;
import com.scheduleapp2.dto.user.UserResponseDto;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.exception.CustomException;
import com.scheduleapp2.exception.ErrorCode;
import com.scheduleapp2.mapper.UserMapper;
import com.scheduleapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User createdUser = userRepository.save(userMapper.toEntity(userRequestDto));
        return userMapper.toResponseDto(createdUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long userId) {
        User foundUser = findUserByIdOrElseThrow(userId);
        return userMapper.toResponseDto(foundUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        User user = findUserByIdOrElseThrow(userId);

        user.updateNameAndEmail(userRequestDto);
        userRepository.saveAndFlush(user);

        return userMapper.toResponseDto(user);
    }

    @Transactional
    public void deleteUserById(Long userId) { userRepository.deleteById(userId); }

    // (email, password)로 찾은 user id 반환, 실패시 USER_NOT_FOUND 예외 처리
    @Transactional(readOnly = true)
    public Long login(UserLoginRequestDto userLoginRequestDto) {
        String loginEmail = userLoginRequestDto.email();
        String loginPassword = userLoginRequestDto.password();

        Optional<User> loginUser = userRepository.findByEmailAndPassword(loginEmail, loginPassword);
        return loginUser.map(User::getId).orElseThrow(() -> new CustomException(ErrorCode.USER_LOGIN_FAIL));
    }

    // findById로 찾은 entity 반환, 조회 실패시 예외 처리
    public User findUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
