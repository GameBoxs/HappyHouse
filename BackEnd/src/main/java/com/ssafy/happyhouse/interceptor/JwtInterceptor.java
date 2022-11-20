package com.ssafy.happyhouse.interceptor;

import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("JWT [{}]", requestURI);

        //쿠기가 없으면 리턴
        if (request.getCookies() == null) {
            log.debug("NO COOKIES");
//            response.sendRedirect("/users/login?redirect=" + requestURI);
            return false;
        }
        Optional<Cookie> jwt = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtConst.JWT_HEADER))
                .findFirst();

        //토큰이 없으면 로그인 페이지로 리다이렉트
        if (jwt.isEmpty()) {
            log.debug("NO TOKEN REDIRECT [{}]", requestURI);
//            response.sendRedirect("/users/login?redirect=" + requestURI);
            return false;
        }

        String token = jwt.get().getValue();
        log.debug("token [{}]", token);

        String userEmail = jwtProvider.extractUsername(token);
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if (findUser.isEmpty()) {
            log.debug("NO USER REDIRECT [{}][{}]", requestURI, userEmail);
//            response.sendRedirect("/users/login?redirect=" + requestURI);
            return false;
        }

        return true;
    }
}
