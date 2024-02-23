package com.api.gestion.demo.security.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuthService {

    ResponseEntity<String> singUp(Map<String, String> requestmap);
    ResponseEntity<String> login(Map<String, String> requestmap);

}
