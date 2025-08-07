package com.scheduleapp2.controller;

import com.scheduleapp2.dto.user.UserRequestDto;
import com.scheduleapp2.dto.user.UserResponseDto;
import com.scheduleapp2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUserDto = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    // 단일 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        UserResponseDto foundUserDto = userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(foundUserDto);
    }

    // 유저 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto updatedUserDto = userService.updateUser(userId, userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDto);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
