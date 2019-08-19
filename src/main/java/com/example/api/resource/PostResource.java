package com.example.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostResource {

    private long postId;
    private String title;
    private String body;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long id) {
        this.postId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public PostResource() {
    }

    public PostResource(long postId, String title, String body) {
        this.postId = postId;
        this.title = title;
        this.body = body;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PostResource && ((PostResource) obj).getPostId() == this.postId)? true :false;
    }


}
