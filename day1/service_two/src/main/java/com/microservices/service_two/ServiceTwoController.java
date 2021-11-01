package com.microservices.service_two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceTwoController {
@Autowired
Environment env;
	
	
	@GetMapping("/service-two/get")
	public String getMapping() {
		String port=env.getProperty("local.server.port");
		return  ("From Service Two Get  PORT:"+port);
	}
	
}
