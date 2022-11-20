package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.dto.UserDTO;
import com.ssafy.happyhouse.domain.dto.UserLoginDTO;
import com.ssafy.happyhouse.interceptor.JwtConst;
import com.ssafy.happyhouse.security.JwtProvider;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    //TODO : 변경은 비번만

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping
    public Object saveUser(@Validated UserDTO userDTO) {
        userService.save(userDTO);
        return "ok";
    }

    @PostMapping("/login")
    public Object login(@Validated UserLoginDTO userLoginDTO, HttpServletResponse response) {
        String jwtToken = jwtProvider.generateToken(userLoginDTO.getEmail());
        Cookie cookie = new Cookie(JwtConst.JWT_HEADER, jwtToken);
        cookie.setPath("/");
        response.addCookie(cookie);

        log.info("JWT-TOKEN [{}]",jwtToken);

        return "ok";
    }

    @GetMapping("/logout")
    public Object logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(JwtConst.JWT_HEADER, "");
        cookie.setPath("/");
        response.addCookie(cookie);

        return "ok";
    }

    @GetMapping("/{userid}")
    public UserDTO userInfo(@PathVariable Long userid) {
        UserDTO userInfo = userService.getUserInfo(userid);
        return userInfo;
    }

    //TODO : 유저 정보 수정?
}
