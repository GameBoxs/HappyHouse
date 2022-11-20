package com.ssafy.happyhouse.config;

import com.ssafy.happyhouse.argumentresolver.LoginMemberArgumentResolver;
import com.ssafy.happyhouse.interceptor.JwtInterceptor;
import com.ssafy.happyhouse.interceptor.LogInterceptor;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**");

        registry.addInterceptor(new JwtInterceptor(jwtProvider, userRepository))
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/users","/users/login", "/users/logout", "/boards", "/boards/**", "/error");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .allowedHeaders("headers")
                .maxAge(3000);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver(jwtProvider, userRepository));
    }
}
