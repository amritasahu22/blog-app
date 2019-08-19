package com.example.api;

import com.example.api.model.Post;
import com.example.api.model.User;
import com.example.api.resource.PostResource;
import com.example.api.resource.UserResource;
import com.example.api.service.ConsumePostWebService;
import com.example.api.service.ConsumeUserWebService;
import com.example.api.service.UsersAndPostsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersAndPostsServiceTest {

    @Mock
    private ConsumeUserWebService userWS;

    @Mock
    private ConsumePostWebService postWS;

    @InjectMocks
    private UsersAndPostsService usersandPostsWS;

    private List<UserResource> usersAndPostsList;
    private User[] usersTestData;
    private Post[] postsTestData;

    @Before
    public void setupMockData() {
        usersTestData = new User[]{new User(1L, "Rick Martin"), new User(2L, "MJ Katty")};
        postsTestData = new Post[]{
                new Post(1L, 1L, "Weather today", "And it is cloudy!"),
                new Post(2L, 1L, "Hygiene today", "Needs improvement!")
        };

        usersAndPostsList = Arrays.asList(
                new UserResource(1L, "Rick Martin", Arrays.asList(new PostResource(1L, "Weather today", "And it is cloudy!"),
                        new PostResource(1L, "Weather today", "it is black and dark now..."))),
                new UserResource(2L, "MJ Katty", Arrays.asList(new PostResource(2L, "Weather today", "Will it rain?")))
        );

    }


    @Test
    public void whenUsersAndPostsServiceInvoked_shouldReturnUsersAndPostsMockedObject() throws Exception {

        Mockito.when(userWS.getAllUsers()).thenReturn(Arrays.asList(usersTestData));
        Mockito.when(postWS.getAllPosts()).thenReturn(Arrays.asList(postsTestData));

        List<UserResource> userResourceMockList = usersandPostsWS.getUsersAndPosts();
        assertThat(userResourceMockList, hasSize(2));
        //Actual, Expected
        assertThat(userResourceMockList, equalTo(usersAndPostsList));
    }

    @Test(expected = RuntimeException.class)
    public void whenUsersAndPostsServiceServiceInvoked_shouldReturnException() throws Exception {
        Mockito.when(usersandPostsWS.getUsersAndPosts()).thenThrow(new RuntimeException());
        List<UserResource> usersAndPosts = usersandPostsWS.getUsersAndPosts();
        assertThat(usersAndPosts, null);
    }
}