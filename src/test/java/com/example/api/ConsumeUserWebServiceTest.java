package com.example.api;

import com.example.api.model.User;
import com.example.api.service.ConsumeUserWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsumeUserWebServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConsumeUserWebService userWS;

    private ResponseEntity response;

    private User[] userTest;

    @Before
    public void setup() {
        userTest = new User[]{new User(1L, "Rick Martin"), new User(2L, "MJ Katty")};
        response = new ResponseEntity(userTest, HttpStatus.OK);
    }

    @Test
    public void whenGetAllUsersCalled_shouldReturnUserMockedObject() throws Exception {

        Mockito
                .when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class))
                .thenReturn(response);


        List<User> users = userWS.getAllUsers();

        //Expected, Actual
        assertThat(users, hasSize(2));
        assertThat(Arrays.asList(userTest), equalTo(users));
    }

    @Test(expected = Exception.class)
    public void whenGetAllUsersCalled_shouldThrowException() throws Exception {
        Mockito
                .when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class))
                .thenReturn(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR));

        List<User> users = userWS.getAllUsers();
        assertThat(users, null);
    }

}
