package com.nagarro.microservices.productservice.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		doSomething();
	}

	private void doSomething() {
		System.out.println("I am dancing!!!");
	}

}