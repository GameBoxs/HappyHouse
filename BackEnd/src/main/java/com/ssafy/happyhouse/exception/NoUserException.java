package com.ssafy.happyhouse.exception;

public class NoUserException extends RuntimeException{
    public NoUserException() {
    }
    public NoUserException(String message) {
        super(message);
    }
}
