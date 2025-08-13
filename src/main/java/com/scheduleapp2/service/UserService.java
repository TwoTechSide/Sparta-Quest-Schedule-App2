package com.scheduleapp2.service;

import com.scheduleapp2.common.config.PasswordEncoder;
import com.scheduleapp2.dto.user.*;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.common.exception.CustomException;
import com.scheduleapp2.common.exception.ErrorCode;
import com.scheduleapp2.mapper.UserMapper;
import com.scheduleapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder pwEncoder;

    @Transactional
    public UserResponseDto createUser(UserSignupRequestDto userSignupRequestDto) {
        // User Entity의 email(unique = true) 필드가 중복되는 경우, DataIntegrityViolationException 예외 처리
        try {
            String encodedPassword = pwEncoder.encode(userSignupRequestDto.password());
            User createdUser = userRepository.save(userMapper.toEntity(userSignupRequestDto, encodedPassword));
            return userMapper.toResponseDto(createdUser);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorCode.USER_SIGNUP_FAIL);
        }
    }

    public UserResponseDto findUserById(Long userId) {
        User foundUser = findUserByIdOrElseThrow(userId);
        return userMapper.toResponseDto(foundUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = findUserByIdOrElseThrow(userId);

        user.updateNameAndEmail(userUpdateRequestDto);
        userRepository.saveAndFlush(user);

        return userMapper.toResponseDto(user);
    }

    @Transactional
    public void deleteUserById(Long userId) { userRepository.deleteById(userId); }

    // (email, password)로 찾은 user id 반환, 실패시 USER_LOGIN_FAIL 예외 처리
    public Long login(UserLoginRequestDto userLoginRequestDto) {
        String loginEmail = userLoginRequestDto.email();
        String loginPassword = userLoginRequestDto.password();

        Optional<User> loginUser = userRepository.findByEmail(loginEmail);

        if (loginUser.isPresent()) {
            String userPassword = loginUser.get().getPassword();

            if (pwEncoder.matches(loginPassword, userPassword)) {
                return loginUser.get().getId();
            }
        }

        throw new CustomException(ErrorCode.USER_LOGIN_FAIL);
    }

    // findById로 찾은 entity 반환, 조회 실패시 예외 처리
    public User findUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
