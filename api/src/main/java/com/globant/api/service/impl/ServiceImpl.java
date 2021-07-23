package com.globant.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.api.client.IClient;
import com.globant.api.service.IApiService;

@Service
public class ServiceImpl implements IApiService {

	@Autowired
	private IClient client;

	public String getMessage() {
		return client.getMessage();
	}
}
