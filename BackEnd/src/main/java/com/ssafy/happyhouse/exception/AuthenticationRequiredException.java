package com.ssafy.happyhouse.exception;

public class AuthenticationRequiredException extends RuntimeException{
    public AuthenticationRequiredException() {
    }

    public AuthenticationRequiredException(String message) {
        super(message);
    }
}
