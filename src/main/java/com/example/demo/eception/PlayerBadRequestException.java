package com.example.demo.eception;

public class PlayerBadRequestException extends RuntimeException {
    public PlayerBadRequestException() {
    }

    public PlayerBadRequestException(String message) {
        super(message);
    }
}
