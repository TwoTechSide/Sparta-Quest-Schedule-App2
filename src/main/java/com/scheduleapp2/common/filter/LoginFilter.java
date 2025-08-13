package com.scheduleapp2.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.scheduleapp2.dto.ErrorResponse;
import com.scheduleapp2.common.exception.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/users/signup", "/users/login", "/users/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        // 1. 화이트 리스트(String[] WHITE_LIST)에 포함되지 않은 URI인 경우
        // 2. session이 없거나, 유저가 로그인한 경우가 아니면(LOGIN_USER == null)
        if (!isWhiteList(requestURI)) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("LOGIN_USER") == null) {

                // jackson 라이브러리가 Java 8의 LocalDateTime을 지원하지 않기 때문에 모듈 추가
                final ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());

                // 3. ErrorResponse ( USER_NOT_LOGIN, UNAUTHORIZED ) 를 json 으로 변환한 뒤, response에 담아서 반환
                final ErrorCode errorCode = ErrorCode.USER_NOT_LOGIN;

                ErrorResponse error = ErrorResponse.of(
                        errorCode.getHttpStatus(),
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        request.getRequestURI());

                response.setStatus(errorCode.getHttpStatus().value());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(error));
                return;
            }
        }

        // 4. 다음 필터가 없으면 Servlet -> Controller 호출
        filterChain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
