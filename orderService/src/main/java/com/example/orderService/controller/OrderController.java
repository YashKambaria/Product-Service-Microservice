package com.example.orderService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final RestTemplate restTemplate;
	
	public OrderController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable String id){
		String uri="http://localhost:8082/products/"+id;
		String response=restTemplate.getForObject(uri,String.class);
		ResponseEntity<String> response1=restTemplate.getForEntity(uri,String.class);
		
		System.out.println("Response from api "+response1.getStatusCode());
		return ResponseEntity.ok(response1.getBody());
	}
	
	
	
	
}
