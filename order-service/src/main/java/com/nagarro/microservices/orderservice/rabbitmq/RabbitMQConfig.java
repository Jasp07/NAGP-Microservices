package com.nagarro.microservices.orderservice.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	static final String topicExchange = "spring-boot-exchange";
	
	static final String queueName = "spring-boot";
	
	static final String routingKey = "product.#";
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchange);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
}
