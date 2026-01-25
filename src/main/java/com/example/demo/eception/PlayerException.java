package com.example.demo.eception;

public class PlayerException {

    public static class NotFound extends RuntimeException {}

    public static class BadRequest extends RuntimeException {}
}
