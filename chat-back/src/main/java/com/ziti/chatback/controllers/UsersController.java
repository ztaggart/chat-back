package com.ziti.chatback.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("")
    public String helloUserController(){
        return "User access level test";
    }
}
