package com.globant.api.controller.controllerImpl;

import com.globant.api.controller.IApiController;
import com.globant.api.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControllerImpl implements IApiController {

    @Autowired
    private IApiService service;

    @Override
    public String getMessage() {
        return service.getMessage();
    }


}
