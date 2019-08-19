package com.example.api.resource;

import java.util.ArrayList;
import java.util.List;

public class UserResource {

    private long userId;
    private String name;
    private List<PostResource> posts = new ArrayList<>();

    public UserResource() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostResource> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResource> posts) {
        this.posts = posts;
    }


    public UserResource(long userId, String name, List<PostResource> posts) {
        this.userId = userId;
        this.name = name;
        this.posts = posts;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof UserResource && ((UserResource) obj).getUserId() == this.userId) ? true : false;
    }

}
