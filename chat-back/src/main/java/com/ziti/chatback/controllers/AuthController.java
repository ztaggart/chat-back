package com.ziti.chatback.controllers;

import com.ziti.chatback.dtos.LoginResponseDto;
import com.ziti.chatback.dtos.RegistrationDto;
import com.ziti.chatback.dtos.UserDto;
import com.ziti.chatback.entities.User;
import com.ziti.chatback.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/register")
    public RegistrationDto registerUser(@RequestBody UserDto userDto) {
        User user = authenticationService.registerUser(userDto.getUsername(), userDto.getPassword());
        return new RegistrationDto(user.getUserId(), user.getUsername());
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody UserDto userDto) {
        return authenticationService.login(userDto.getUsername(), userDto.getPassword());
    }
}
