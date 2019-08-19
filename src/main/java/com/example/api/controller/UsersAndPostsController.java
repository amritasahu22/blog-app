package com.example.api.controller;

import com.example.api.resource.UserResource;
import com.example.api.service.ConsumePostWebService;
import com.example.api.service.UsersAndPostsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersAndPostsController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumePostWebService.class);

    @Autowired
    private UsersAndPostsService usersAndPostsService;

    @RequestMapping(value = "/usersandposts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsersAndPosts() {
        logger.debug("Entering UsersAndPostsController:getUsersAndPosts()");
        List<UserResource> usersAndPostsList = null;
        try {
            //Call Service
            usersAndPostsList = usersAndPostsService.getUsersAndPosts();
        } catch (Exception e) {
            logger.error("Exception in UsersAndPostsController:getUsersAndPosts():" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        logger.debug("Exiting UsersAndPostsController:getUsersAndPosts()");
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(usersAndPostsList);
    }
}
