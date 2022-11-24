package com.ssafy.happyhouse.interceptor;

import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.exception.AuthenticationRequiredException;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.security.JwtConst;
import com.ssafy.happyhouse.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
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
        String method = request.getMethod();

        if (HttpMethod.OPTIONS.matches(method)) {
            return true;
        }

        if (requestURI.matches("\\/boards") && method.matches(HttpMethod.GET.name())) {
            return true;
        }

        if (requestURI.matches("\\/boards\\/[0-9]+") && method.matches(HttpMethod.GET.name())) {
            return true;
        }

        if(requestURI.matches("\\/favorite\\/[0-9]+") && method.matches(HttpMethod.GET.name())){
            return true;
        }

        log.info("JWT [{}]", requestURI);

        //쿠기가 아예 없으면
        if (request.getCookies() == null) {
            log.debug("NO COOKIES");
            throw new AuthenticationRequiredException("로그인이 필요합니다.");
        }

        Optional<Cookie> jwt = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtConst.JWT_HEADER))
                .findFirst();

        //jwt 쿠키가 없으면
        if (jwt.isEmpty()) {
            log.debug("NO TOKEN REDIRECT [{}]", requestURI);
            throw new AuthenticationRequiredException("로그인이 필요합니다.");
        }

        String token = jwt.get().getValue();
        log.debug("token [{}]", token);

        String userEmail = jwtProvider.extractEmail(token);
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        if (findUser.isEmpty()) { //토큰이 잘못되어있으면
            log.debug("INVALIDATE TOKEN [{}][{}]", requestURI, userEmail);
            throw new AuthenticationRequiredException("토큰이 유요하지 않습니다.");
        }

        return true;
    }
}
