package com.example.api;

import com.example.api.controller.UsersAndPostsController;
import com.example.api.resource.PostResource;
import com.example.api.resource.UserResource;
import com.example.api.service.UsersAndPostsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = UsersAndPostsController.class)
public class UsersAndPostsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsersAndPostsService usersAndPostsService;

    @InjectMocks
    private UsersAndPostsController usersAndPostsController;

    private ObjectMapper mapper = new ObjectMapper();

    private String expectedResponse;

    private List<UserResource> usersAndPostsList;

    @Before
    public void setupMockData() {
        mockMvc = standaloneSetup(this.usersAndPostsController).build(); // Standalone context

        expectedResponse = "[{\"userId\":1,\"name\":\"Rick Martin\",\"posts\":[{\"postId\":1,\"title\":\"Weather today\",\"body\":\"And it is cloudy!\"},{\"postId\":2,\"title\":\"Weather today\",\"body\":\"it is black and dark now...\"}]},{\"userId\":2,\"name\":\"MJ Katty\",\"posts\":[{\"postId\":1,\"title\":\"Weather today\",\"body\":\"Will it rain?\"}]}]";

        usersAndPostsList = Arrays.asList(
                new UserResource(1L, "Rick Martin", Arrays.asList(new PostResource(1L, "Weather today", "And it is cloudy!"),
                        new PostResource(2L, "Weather today", "it is black and dark now..."))),
                new UserResource(2L, "MJ Katty", Arrays.asList(new PostResource(1L, "Weather today", "Will it rain?")))
        );
    }


    @Test
    public void whenControllerCalled_shouldReturnJson() throws Exception {
        Mockito.when(usersAndPostsService.getUsersAndPosts()).thenReturn(usersAndPostsList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/usersandposts")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
        Assert.assertEquals(expectedResponse, result.getResponse().getContentAsString());
    }


    @Test
    public void whenControllerCalled_shouldReturnServerError() throws Exception {

        Mockito.when(usersAndPostsService.getUsersAndPosts()).thenThrow(new Exception());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/usersandposts")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }
}