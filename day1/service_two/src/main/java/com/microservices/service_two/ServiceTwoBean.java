package com.microservices.service_two;

public class ServiceTwoBean {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceTwoBean(String message) {
		super();
		this.message = message;
	}

}
