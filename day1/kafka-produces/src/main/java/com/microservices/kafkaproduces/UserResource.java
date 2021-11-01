package com.microservices.kafkaproduces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {
@Autowired
KafkaTemplate<String, String> kafkaTemplateString;
	@Autowired
	KafkaTemplate<String, User> kafkaTemplateUser;
private static final String TOPIC="KAFKA_STRING";
private static final String TOPIC2="KAFKA_JSON";
	
@GetMapping("/publish/{message}")
	public String post(@PathVariable String message) {
	kafkaTemplateString.send(TOPIC,message);
		return "publsiged suuccessfully";
	}
	@GetMapping("/publish/id/{id}/name/{name}")
	public String postJson(@PathVariable int id,@PathVariable String name) {
		User u=new User(id,name);
		kafkaTemplateUser.send(TOPIC2,u);
		return "publsiged suuccessfully";
	}
	@GetMapping("/")
	public String get() {
		return "Ready";
	}
}
