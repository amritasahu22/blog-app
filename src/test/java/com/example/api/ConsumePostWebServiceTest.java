package com.example.api;

import com.example.api.model.Post;
import com.example.api.service.ConsumePostWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@RunWith(MockitoJUnitRunner.class)
public class ConsumePostWebServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConsumePostWebService postWS;

    private ResponseEntity successResponse;
    private ResponseEntity errorResponse;

    private Post[] postTest;

    @Before
    public void setupMockData() {
        postTest = new Post[]{
                new Post(1L, 1L, "Weather today", "And it is cloudy!"),
                new Post(2L, 1L, "Weather today", "Will it rain?")
        };

        successResponse = new ResponseEntity(postTest, HttpStatus.OK);
        errorResponse = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void whenGetAllPostsCalled_shouldReturnPostMockedObject() throws Exception {
        Mockito
                .when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Post[].class))
                .thenReturn(successResponse);

        List<Post> posts = postWS.getAllPosts();

        assertThat(posts, hasSize(2));
        //Actual, Expected
        assertThat(Arrays.asList(postTest), equalTo(posts));
    }

    @Test(expected = Exception.class)
    public void whenGetAllPostsCalled_shouldThrowException() throws Exception {
        Mockito
                .when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Post[].class))
                .thenReturn(errorResponse);

        List<Post> posts = postWS.getAllPosts();
        assertThat(posts, null);
    }

}
