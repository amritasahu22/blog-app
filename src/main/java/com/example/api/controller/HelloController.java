package com.example.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{

    @RequestMapping("/hello")
    public String getMessage(){
        return "Hi Amrita to Spring Boot Application!";
    }


}