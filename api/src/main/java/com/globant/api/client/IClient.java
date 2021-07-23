package com.globant.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Client", url = "${host.url}")
public interface IClient {

	@GetMapping(value = "${host.path.message}")
	String getMessage();

}
