package com.api.gestion.demo.servicios;

import com.api.gestion.demo.user.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {

    ResponseEntity<String> singUp(Map<String, String> requestmap);
}
