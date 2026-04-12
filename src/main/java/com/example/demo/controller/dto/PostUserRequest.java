package com.example.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostUserRequest {

    Long id;
    String name;
    String login;
    String email;
    String password;
    LocalDate registration;
    LocalDate lastLogin;
}
