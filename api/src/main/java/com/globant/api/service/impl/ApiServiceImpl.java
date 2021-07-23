package com.globant.api.service.impl;

import com.globant.api.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.api.client.IClient;

@Service
class ServiceImpl implements IApiService {

	@Autowired
	private IClient client;

	public String getMessage() {
		return client.getMessage();
	}
}
