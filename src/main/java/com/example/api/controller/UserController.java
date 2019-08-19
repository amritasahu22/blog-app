package com.example.api.controller;

import com.example.api.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping("/users")
    public List<User> getAllUsers(){
        List<User> users = Arrays.asList(
                new User(Long.valueOf("1"),"Riya"),
                new User(Long.valueOf("2"), "Ami")
        );


        return users;

    }
}
