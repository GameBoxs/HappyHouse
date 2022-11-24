package com.ssafy.happyhouse.advice;

import com.ssafy.happyhouse.exception.AuthenticationRequiredException;
import com.ssafy.happyhouse.exception.NoHouseException;
import com.ssafy.happyhouse.exception.NoUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage noUserException(NoUserException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(403)
                .build();
    }

    @ExceptionHandler(NoHouseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage noHouseException(NoHouseException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(400)
                .build();
    }

    @ExceptionHandler(AuthenticationRequiredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage authenticationRequiredException(AuthenticationRequiredException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(403)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage illegalArgumentException(IllegalArgumentException ex) {
        return ErrorMessage.builder()
                .massage(ex.getMessage())
                .status(400)
                .build();
    }
}
