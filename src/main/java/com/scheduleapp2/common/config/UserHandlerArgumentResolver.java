package com.scheduleapp2.common.config;

import com.scheduleapp2.common.exception.BusinessException;
import com.scheduleapp2.common.exception.ErrorCode;
import com.scheduleapp2.entity.User;
import com.scheduleapp2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserHandlerArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public User resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {

        Object userId = httpSession.getAttribute("LOGIN_USER_ID");

        if (Objects.isNull(userId)) {
            throw new BusinessException(ErrorCode.INTERVAL_SERVER_ERROR);
        }

        Optional<User> optionalUser = userRepository.findById((Long) userId);
        return optionalUser.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
