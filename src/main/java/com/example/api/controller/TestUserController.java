package com.example.api.controller;

import com.example.api.bean.TestUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/test")
public class TestUserController {

    @RequestMapping("/users")
    public List<TestUser> getAllUsers(){
        List<TestUser> users = Arrays.asList(
                new TestUser("1","Riya"),
                new TestUser("2","XYZ")
        );


        return users;

    }
}
