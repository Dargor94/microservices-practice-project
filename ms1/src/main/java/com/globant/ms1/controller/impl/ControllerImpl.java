package com.globant.ms1.controller.impl;

import com.globant.ms1.controller.IController;
import com.globant.ms1.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class ControllerImpl implements IController {

    @Autowired
    private ServiceImpl service;


    @Override
    public String getMessage() {
        return service.getMessage();
    }

}
