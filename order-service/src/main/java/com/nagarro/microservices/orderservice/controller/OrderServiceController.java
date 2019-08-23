package com.nagarro.microservices.orderservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.microservices.orderservice.Configuration;
import com.nagarro.microservices.orderservice.beans.Order;
import com.nagarro.microservices.orderservice.proxy.ProductBean;
import com.nagarro.microservices.orderservice.proxy.ProductServiceProxy;

@RestController
public class OrderServiceController {

	
	@Autowired 
	ProductServiceProxy productServiceProxy;
	 
	/*
	 * @Autowired Environment env;
	 */

	@Autowired
	Configuration configuration;

	@GetMapping("/orders/{orderId}")
	public Order getOrderDetails(@PathVariable String orderId) {
		List<ProductBean> list = productServiceProxy.fetchProductsList();

		Order result = new Order(orderId, list, 500d);
		result.setSla(configuration.getSla());
		return result;
	}

	/*
	 * @GetMapping("/hysterixExample")
	 * 
	 * @HystrixCommand(fallbackMethod = "fallbackGetOrders") public Order
	 * getOrders() { throw new RuntimeException("Hysterix Example"); }
	 */

	public Order fallbackGetOrders() {
		List<ProductBean> list = new ArrayList<>();
		return new Order("fallback", list, -1d);
	}

}