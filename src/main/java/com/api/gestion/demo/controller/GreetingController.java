package com.api.gestion.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/greeting")
public class GreetingController {

    @GetMapping("/sayHello")
    public String SayHello() {
        return "Hello from Api Santi";
    }

    @GetMapping("/sayHelloProtect")
    public String SayHelloProtect() {
        return "Hello from Api Santi SayHelloProtect";
    }
}
