package com.ssafy.happyhouse.interceptor;

import com.ssafy.happyhouse.security.JwtProvider;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        Optional<Cookie> jwt = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtConst.JWT_HEADER))
                .findFirst();
        //토큰이 없으면 로그인 페이지로 리다이렉트
        if (jwt.isEmpty()) {
            response.sendRedirect("/login?redirect=" + requestURI);
            return false;
        }

        String token = jwt.get().getValue();
        log.debug("token = {}", token);

        String userEmail = jwtProvider.extractUsername(token);

        return true;
    }
}
