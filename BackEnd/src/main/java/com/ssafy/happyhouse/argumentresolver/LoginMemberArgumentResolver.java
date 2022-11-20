package com.ssafy.happyhouse.argumentresolver;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.interceptor.JwtConst;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("SUPPORT");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasUserType = User.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        Cookie jwtCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtConst.JWT_HEADER))
                .findAny()
                .orElse(null);

        if (jwtCookie == null) {
            log.warn("NO JET TOKEN");
            return null;
        }

        String jwtToken = jwtCookie.getValue();
        String email = jwtProvider.extractEmail(jwtToken);
        User user = userRepository.findByEmail(email)
                .orElse(null);

        return user;
    }
}
