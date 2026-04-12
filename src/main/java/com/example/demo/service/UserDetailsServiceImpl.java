package com.example.demo.service;

import com.example.demo.mapper.ServiceRepositoryMapper;
import com.example.demo.repository.UserRepositoryJpa;
import com.example.demo.repository.entity.User;
import com.example.demo.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryJpa userRepositoryJpa;
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepositoryJpa userRepositoryJpa, PasswordEncoder passwordEncoder) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryJpa.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void register(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistration(LocalDate.now());
        User newUser = ServiceRepositoryMapper.mapToUserEntity(user);
        userRepositoryJpa.save(newUser);
    }

    public UserDto findUserByLogin(String login) {
        User user = userRepositoryJpa.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
        UserDto userDto = ServiceRepositoryMapper.mapToUserEntity(user);
        return userDto;
    }

    public void save(UserDto user) {
        User updatedUser = ServiceRepositoryMapper.mapToUserEntity(user);
        userRepositoryJpa.save(updatedUser);
    }
}
