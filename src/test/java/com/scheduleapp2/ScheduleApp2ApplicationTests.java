package com.scheduleapp2;

import com.scheduleapp2.dto.schedule.ScheduleRequestDto;
import com.scheduleapp2.dto.user.UserRequestDto;
import com.scheduleapp2.service.ScheduleService;
import com.scheduleapp2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class ScheduleApp2ApplicationTests {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    UserService userService;

    /**
     * Transactional 어노테이션 사용시, Test가 끝나면 DB에 데이터 저장되지 않음
     */

    // @Transactional
    @Test
    void createUser() {
        UserRequestDto userRequestDto = new UserRequestDto("유저2", "이메일2", "비밀번호2");
        userService.createUser(userRequestDto);
    }

    // @Transactional
    @Test
    void createSchedule() {
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto("작성자1", "제목1", "내용1");
        scheduleService.createSchedule(scheduleRequestDto, 1L);
    }

}
