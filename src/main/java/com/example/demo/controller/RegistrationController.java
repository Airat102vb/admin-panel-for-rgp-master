package com.example.demo.controller;

import com.example.demo.controller.dto.PostUserRequest;
import com.example.demo.mapper.ControllerServiceMapper;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserDetailsServiceImpl userService;

    public RegistrationController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody PostUserRequest user) {
        UserDto newUser = ControllerServiceMapper.mapToUserDto(user);
        userService.register(newUser);
        return "User registered successfully!";
    }
}
