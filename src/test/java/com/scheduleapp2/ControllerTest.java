package com.scheduleapp2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    MockMvc mvc;

    private static final String BASE_URL = "/comments";

    @Test
    @DisplayName("댓글 조회 (일정 id)")
    void getAllCommentsByScheduleIdTest() throws Exception {

        Long scheduleId = 1L;

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/schedules/" + scheduleId))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
