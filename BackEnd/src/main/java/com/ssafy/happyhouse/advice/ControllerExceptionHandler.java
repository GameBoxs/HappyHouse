package com.ssafy.happyhouse.advice;

import com.ssafy.happyhouse.exception.AuthenticationRequiredException;
import com.ssafy.happyhouse.exception.NoHouseException;
import com.ssafy.happyhouse.exception.NoUserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoUserException.class)
    public ErrorMessage noUserException(NoUserException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(403)
                .build();
    }

    @ExceptionHandler(NoHouseException.class)
    public ErrorMessage noHouseException(NoHouseException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(400)
                .build();
    }

    @ExceptionHandler(AuthenticationRequiredException.class)
    public ErrorMessage authenticationRequiredException(AuthenticationRequiredException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(403)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorMessage illegalArgumentException(IllegalArgumentException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(400)
                .build();
    }
}
