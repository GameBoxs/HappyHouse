package com.ssafy.happyhouse.config;

import com.ssafy.happyhouse.argumentresolver.LoginMemberArgumentResolver;
import com.ssafy.happyhouse.interceptor.JwtInterceptor;
import com.ssafy.happyhouse.interceptor.LogInterceptor;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
//@EnableWebMvc
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
                .excludePathPatterns("/", "/users", "/users/login", "/users/logout",
                        "/users/email", "/boards", "/boards/**", "/dongcode/**",
                        "/housedeal/**", "/houseinfo/**", "/comments/**", "/favorite/**",
                        "/js/**", "/css/**","/img/**", "/index.html",
                        "/error","/news/search","/password");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET", "POST", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3000);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver(jwtProvider, userRepository));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js")
                .addResourceLocations("classpath:/resources/static/js/**");
        registry.addResourceHandler("/css")
                .addResourceLocations("classpath:/resources/static/css/**");
        registry.addResourceHandler("/img")
                .addResourceLocations("classpath:/resources/static/img/**");
    }
}
