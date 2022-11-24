package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/password")
public class PasswordController {

    private final EmailService emailService;

    @GetMapping
    public boolean sendEmail(@RequestParam String email) {
        return emailService.sendMail(email);
    }

}
