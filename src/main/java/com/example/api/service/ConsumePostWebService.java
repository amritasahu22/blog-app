package com.example.api.service;

import com.example.api.util.UtilConstants;
import com.example.api.model.Post;
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
public class ConsumePostWebService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumePostWebService.class);

    @Autowired
    RestTemplate restTemplate;

    private List<Post> postList;

    public List<Post> getAllPosts() throws Exception{
        logger.debug("Entering ConsumePostWebService:getAllPosts()");

        ResponseEntity<Post[]>  response = restTemplate.getForEntity(UtilConstants.CONSUME_POSTS_URL, Post[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody().length != 0 ) {
            postList = Arrays.asList(response.getBody());
            logger.debug("Rest service response:: " + UtilConstants.CONSUME_POSTS_URL+"->Status:"+response.getStatusCode()+", Response:"+response.getBody());

        }else{
            logger.error("Rest service Response:: "+ UtilConstants.CONSUME_USERS_URL+"->Status: "+response.getStatusCode()+", Error: "+response.getStatusCode().getReasonPhrase());
            throw new Exception("Exception in ConsumePostWebService:getAllPosts():: "+ response.getStatusCode().getReasonPhrase());
        }

        logger.debug("Exiting ConsumePostWebService:getAllPosts()");
        return postList;
    }

}
