package com.globant.api.mock.service;


import com.globant.api.service.impl.ApiServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ApiServiceImplTest {

    @MockBean
    private ApiServiceImpl apiService;

    @Test
    public void getMessageTest_shouldReturnHelloMessage() {

        Mockito.when(apiService.getMessage()).thenReturn("Hello from a microservice");
        String message = apiService.getMessage();
        Assert.assertEquals("Hello from a microservice", message);
        Mockito.verify(apiService).getMessage();

    }
}
