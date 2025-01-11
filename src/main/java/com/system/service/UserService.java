package com.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.configuration.JWTHandler;
import com.system.model.UserModel;

@Service
public class UserService {

    private final JWTHandler jwtHandler;

    @Autowired
    public UserService(JWTHandler jwtHandler) {
        this.jwtHandler = jwtHandler;
    }

    public String login(UserModel user) {
        return jwtHandler.generation(user);
    }
}
