package com.globant.api.service.impl;

import com.globant.api.client.IClient;
import com.globant.api.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements IApiService {

    @Autowired
    private IClient client;

    public String getMessage() {
        return client.getMessage();
    }
}
