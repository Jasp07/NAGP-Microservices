package com.nagarro.microservices.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.microservices.productservice.model.Products;

@RestController
public class ProductServiceController {
	
	@Autowired
	Environment env;

	@GetMapping("/products")
	public List<Products> getProducts(){
		List<Products> productList = new ArrayList<Products>();
		Products p1 = new Products(1, "product1", 200D, "A");
		p1.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		productList.add(p1);
		Products p2 = new Products(2,"product2",300D,"B");
		p2.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		productList.add(p2);
		return productList;
	}
}
