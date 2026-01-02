package com.example.productService.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	@GetMapping("/{id}")
	public String getProduct(@PathVariable String id){
		return "Product fetched with id "+id;
	}
	
}
