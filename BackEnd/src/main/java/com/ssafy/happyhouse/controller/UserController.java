package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.dto.UserDTO;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Object saveUser(@Validated UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldErrors().stream().map(error -> error.getField());
        }
        userService.save(userDTO);
        return "ok";
    }

    @GetMapping("/{userid}")
    public UserDTO userInfo(@PathVariable Long userid) {
        UserDTO userInfo = userService.getUserInfo(userid);
        return userInfo;
    }
}
