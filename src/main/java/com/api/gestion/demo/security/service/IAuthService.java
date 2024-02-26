package com.api.gestion.demo.security.service;

import com.api.gestion.demo.dto.LoginDTO;
import com.api.gestion.demo.entitys.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface IAuthService {

    HashMap<String, String> login(LoginDTO loginRequest) throws Exception;

    HashMap<String, String> register(UserEntity user) throws Exception;
}
