package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.dto.PasswordDTO;
import com.ssafy.happyhouse.domain.dto.UserDTO;
import com.ssafy.happyhouse.domain.dto.UserLoginDTO;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.exception.NoUserException;
import com.ssafy.happyhouse.security.JwtConst;
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
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping
    public Object saveUser(@Validated @RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return "ok";
    }

    @PostMapping("/login")
    public Object login(@Validated @RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        if (!userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword())) {
            throw new IllegalArgumentException("NO MATCH");
        }

        String jwtToken = jwtProvider.generateToken(userLoginDTO);
        Cookie cookie = new Cookie(JwtConst.JWT_HEADER, jwtToken);
        cookie.setPath("/");
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);

        log.info("JWT-TOKEN [{}]", jwtToken);

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

    @PatchMapping("/password")
    public String changePassword(@Login User user, @RequestBody PasswordDTO password) {
        userService.updatePassword(user.getId(), password.getPassword());
        return "ok";
    }

    @GetMapping("/email")
    public boolean validateEmail(@RequestParam String email) {
        return userService.validateEmail(email);
    }
}
