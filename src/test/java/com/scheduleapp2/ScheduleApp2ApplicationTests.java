package com.scheduleapp2;

import com.scheduleapp2.dto.comment.CommentCreateRequestDto;
import com.scheduleapp2.dto.schedule.ScheduleCreateRequestDto;
import com.scheduleapp2.dto.user.UserSignupRequestDto;
import com.scheduleapp2.service.CommentService;
import com.scheduleapp2.service.ScheduleService;
import com.scheduleapp2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ScheduleApp2ApplicationTests {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    /**
     * Transactional 어노테이션 사용시, Test가 끝나면 DB에 데이터 저장되지 않음
     */

    // @Transactional
    @Test
    void createUser() {
        UserSignupRequestDto userSignupRequestDto = new UserSignupRequestDto("2TS", "2TS@naver.com", "1aA!");
        userService.createUser(userSignupRequestDto);
    }

    // @Transactional
    @Test
    void createSchedule() {
        ScheduleCreateRequestDto scheduleCreateRequestDto = new ScheduleCreateRequestDto("제목", "내용");
        scheduleService.createSchedule(scheduleCreateRequestDto, 1L);
    }

    // @Transactional
    @Test
    void createComment() {
        CommentCreateRequestDto commentCreateRequestDto = new CommentCreateRequestDto("댓글내용1");
        commentService.createComment(commentCreateRequestDto, 1L, 1L);
    }
}
