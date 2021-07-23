package com.globant.api.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface IApiController {

	@GetMapping("/getMessage")
	String getMessage();

}
