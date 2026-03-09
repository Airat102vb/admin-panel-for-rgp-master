package com.example.demo.eception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException() {
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
