package com.example.api.service;

import com.example.api.util.UtilConstants;
import com.example.api.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ConsumeUserWebService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumePostWebService.class);

    @Autowired
    RestTemplate restTemplate;

    private List<User> userslist;

    public List<User> getAllUsers() throws Exception {
        logger.debug("Entering ConsumeUserWebService:getAllUsers()");

        ResponseEntity<User[]> response = restTemplate.getForEntity(UtilConstants.CONSUME_USERS_URL, User[].class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody().length != 0) {
            userslist = Arrays.asList(response.getBody());
            logger.debug("Rest service response :: " + UtilConstants.CONSUME_USERS_URL + "->Status: " + response.getStatusCode() + ", Response: " + response.getBody());
        } else {
            logger.error("Rest service response:: " + UtilConstants.CONSUME_USERS_URL + "->Status: " + response.getStatusCode() + ", Error: " + response.getStatusCode().getReasonPhrase());
            throw new Exception("Exception in ConsumeUserWebService:getAllUsers():: " + response.getStatusCode().getReasonPhrase());
        }

        logger.debug("Entering UsersAndPostsService:getUsersAndPosts()");

        return userslist;
    }

}
