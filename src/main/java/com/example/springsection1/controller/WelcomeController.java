package com.example.springsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Application with  Security";
    }
}
