package com.example.orderService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final RestClient restClient;
	
	public OrderController(RestClient restClient) {
		this.restClient = restClient;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable String id){
		String uri="http://localhost:8082/products/"+id;
		
		String body = restClient.get()
				.uri(uri)
				.retrieve()
				.body(String.class);
		
		return ResponseEntity.ok(body);
	}
	
	
	
	
}
