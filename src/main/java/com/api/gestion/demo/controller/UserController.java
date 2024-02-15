package com.api.gestion.demo.controller;

import com.api.gestion.demo.constantes.FacturaConstantes;
import com.api.gestion.demo.service.UserService;
import com.api.gestion.demo.utils.FacturaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/sign-up")
    public final ResponseEntity<String> signUp(@RequestBody Map<String, String> requestMap) {
        try {
            return userService.singUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
