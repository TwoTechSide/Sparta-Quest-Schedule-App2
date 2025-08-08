package com.scheduleapp2.controller;

import com.scheduleapp2.dto.user.UserLoginRequestDto;
import com.scheduleapp2.dto.user.UserRequestDto;
import com.scheduleapp2.dto.user.UserResponseDto;
import com.scheduleapp2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 유저 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signupUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUserDto = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    // 유저 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletRequest request) {
        Long userId = userService.login(userLoginRequestDto);
        HttpSession session = request.getSession();

        session.setAttribute("LOGIN_USER_ID", userId);
        return ResponseEntity.ok().build();
    }

    // 유저 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<Void> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);    // 로그인 하지 않으면 null 반환 (false -> 세션 생성 막기)

        if (session != null) {      // 로그인이 된 경우 (세션이 있으면)
            session.invalidate();   // 해당 세션 삭제
        }
        return ResponseEntity.ok().build();
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
