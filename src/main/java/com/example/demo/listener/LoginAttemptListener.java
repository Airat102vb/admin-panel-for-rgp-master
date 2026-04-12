package com.example.demo.listener;

import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoginAttemptListener {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public LoginAttemptListener(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @EventListener
    public void authenticationListener(AuthenticationSuccessEvent event) {
        String name = event.getAuthentication().getName();
        UserDto userDto = userDetailsService.findUserByLogin(name);
        userDto.setLastLogin(LocalDate.now());
        userDetailsService.save(userDto);
    }
}
