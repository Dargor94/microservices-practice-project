package com.globant.ms1.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface IController {

	@GetMapping("/getMessage")
	String getMessage();
	
	
}
