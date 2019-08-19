package com.example.api.service;

import com.example.api.model.Post;
import com.example.api.model.User;
import com.example.api.resource.PostResource;
import com.example.api.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UsersAndPostsService {
    private static final Logger logger = LoggerFactory.getLogger(UsersAndPostsService.class);

    @Autowired
    private ConsumeUserWebService userService;

    @Autowired
    private ConsumePostWebService postService;

    private List<User> consumeUserList = new ArrayList<>();
    private List<Post> consumePostList = new ArrayList<>();

    public List<UserResource> getUsersAndPosts() throws Exception{
        logger.debug("Entering UsersAndPostsService:getUsersAndPosts()");
        List<UserResource> usersAndPostsList = new ArrayList<>();
        List<PostResource> postsForUserList = null;

        try {
            // Call UserService
            consumeUserList = userService.getAllUsers();

            // Call PostService
            consumePostList = postService.getAllPosts();

            // Merge Data for UsersAndPostsService
            PostResource postResource = null;
            UserResource userResource = null;

            //Check consumeUserList is null/empty othewise iterate over it
            if (consumeUserList != null && !consumeUserList.isEmpty()) {
                for (User consumeUser : consumeUserList) {
                    userResource = new UserResource();
                    userResource.setUserId(consumeUser.getId());
                    userResource.setName(consumeUser.getName());
                    postsForUserList = new ArrayList<>();

                    //Check consumePostList is null/empty othewise iterate over it
                    if (consumePostList != null && !consumePostList.isEmpty()) {
                        for (Post consumePost : consumePostList) {
                            //Check if given consumeUser's Id is equal to consumePost's userId
                            if (consumeUser.getId() == consumePost.getUserId()) {
                                //Add all posts for the matched user
                                postResource = new PostResource();
                                postResource.setPostId(consumePost.getId());
                                postResource.setBody(consumePost.getBody());
                                postResource.setTitle(consumePost.getTitle());
                                postsForUserList.add(postResource);
                            }

                        }
                    }
                    userResource.setPosts(postsForUserList);
                    usersAndPostsList.add(userResource);
                }
            }

        } catch (Exception e) {
            logger.error("Exception in UsersAndPostsService:getUsersAndPosts(): " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

         logger.debug("Exiting UsersAndPostsService:getUsersAndPosts()");
        return usersAndPostsList;
    }

}

