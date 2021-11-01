package com.example.Consul.consule_Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConsuleController {
	@Value("${value.from.consul:local}")
	private String constValue;
}
