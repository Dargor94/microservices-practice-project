package com.globant.ms1.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.globant.ms1.controller.IController;
import com.globant.ms1.service.IService;

@RestController
public class ControllerImpl implements IController {

	@Autowired
	private IService service;


	@Override
	public String getMessage() {
		return service.getMessage();
	}

}
