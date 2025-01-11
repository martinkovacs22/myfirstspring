package com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.UserModel;
import com.system.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService; // A Spring Bean automatikus injektálása

    @PostMapping("/login")
    public String home(@RequestBody UserModel user) {
 
        String jwt = userService.login(user); // A Service használata
        return "Hello World jwt: " + jwt;
    }
    
}
