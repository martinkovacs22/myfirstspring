package com.web.systam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.systam.config.JWTHandler;
import com.web.systam.model.UserModel;


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
