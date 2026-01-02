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
	
	@Autowired
	public RestTemplate restTemplate;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable String id){
		String response=restTemplate.getForObject("http://localhost:8082/products/"+id,String.class);
		System.out.println("Response from api "+response);
		return ResponseEntity.ok(response);
	}
	
	
	
	
}
