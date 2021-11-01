package com.microservices.service_one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceOneController {
	@Autowired
	Environment env;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("client/products")
	public String frontend() {
		String url="http://localhost:9000/ims/products";
		String responseString= this.restTemplate.getForObject(url,String.class);
		String port=env.getProperty("local.server.port");
		return "PORT:"+port+" Server one response:"+responseString;
	}
	
	
}
