package com.example.springsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {


    @GetMapping("/myNotices")
    public String getNoticesDetails(){
        return "Here are the notices details from the db";
    }
}
