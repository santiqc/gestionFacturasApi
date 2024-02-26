package com.api.gestion.demo.security.controller;


import com.api.gestion.demo.dto.LoginDTO;
import com.api.gestion.demo.entitys.UserEntity;
import com.api.gestion.demo.security.service.IAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    private ResponseEntity<HashMap<String, String>> addUser(@RequestBody UserEntity user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws Exception {
        HashMap<String, String> login = authService.login(loginRequest);
        if (login.containsKey("jwt")) {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.UNAUTHORIZED);
        }
    }
}
