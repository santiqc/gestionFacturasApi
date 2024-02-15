package com.api.gestion.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {

    ResponseEntity<String> singUp(Map<String, String> requestmap);
}
