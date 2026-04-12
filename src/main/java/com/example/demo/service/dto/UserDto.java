package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    Long id;
    String name;
    String login;
    String email;
    String password;
    LocalDate registration;
    LocalDate lastLogin;
}
