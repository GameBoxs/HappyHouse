package com.ssafy.happyhouse.exception;

public class NoHouseException extends RuntimeException {
    public NoHouseException() {
    }

    public NoHouseException(String message) {
        super(message);
    }
}
