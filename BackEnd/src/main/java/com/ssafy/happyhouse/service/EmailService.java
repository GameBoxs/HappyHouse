package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.exception.NoUserException;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    private final UserRepository userRepository;

    public boolean sendMail(String emailTo) {
        String emailTitle = "HappyHouse 비밀번호 변경";
        String emailContent = null;

        log.info("EMAIL SENDER [{}]", emailFrom);

        User user = userRepository.findByEmail(emailTo)
                .orElseThrow(() -> new NoUserException("없는 유저입니다."));

        String password = UUID.randomUUID().toString();
        emailContent = password;

        String finalEmailContent = emailContent;
        MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(emailTo);
            message.setFrom(emailFrom);    //env.getProperty("spring.mail.username")
            message.setSubject(emailTitle);
            message.setText(finalEmailContent, true); //ture : html 형식 사용
        };
        try {
            mailSender.send(preparator);
            user.changePassword(password);
            userRepository.save(user);
        } catch (MailException e) {
            log.warn("EMAIL [{}]", e);
            return false;
        }
        return true;
    }
}
