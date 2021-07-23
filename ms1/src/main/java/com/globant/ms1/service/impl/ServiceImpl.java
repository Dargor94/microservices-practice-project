package com.globant.ms1.service.impl;

import org.springframework.stereotype.Service;

import com.globant.ms1.service.IService;

@Service
public class ServiceImpl implements IService {

	@Override
	public String getMessage() {
		return "Hello from a microservice";
	}

}
