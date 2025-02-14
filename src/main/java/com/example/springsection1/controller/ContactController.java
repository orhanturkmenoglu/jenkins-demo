package com.example.springsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {


    @GetMapping("/myContact")
    public String getCardsDetails(){
        return "Here are the contact details from the db";
    }
}
