package com.example.productService.controller;


import com.example.productService.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	HashMap<String, Product> data=new HashMap<>();
	
	
	@GetMapping("/{id}")
	public String getProduct(@PathVariable String id){
		return "Product fetched with id "+id;
	}
	
	@PostMapping
	public ResponseEntity<String> addProduct(
			@RequestBody Product product
	){
		try {
			data.put(product.getId(), product);
			return ResponseEntity.ok("Product Added");
		}
		catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/price/{id}")
	public ResponseEntity<?> getProductPrice(
			@PathVariable String id
	){
		if(data.containsKey(id)){
			int price=data.get(id).getPrice();
			return ResponseEntity.ok(price);
		}
		else{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProduct(
			@PathVariable String id,
			@RequestBody Product product
	){
		Product old=data.get(id);
		if(product.getPrice()>0 && product.getPrice()!=old.getPrice()){
			old.setPrice(product.getPrice());
		}
		if(product.getProductCount()>=0 && product.getProductCount()!=old.getProductCount()){
			old.setProductCount(product.getProductCount());
		}
		if(!product.getName().trim().isBlank() && !product.getName().isEmpty()
		&& !product.getName().equals(old.getName())){
			old.setName(product.getName());
		}
		
		return ResponseEntity.ok("Product updated succesfully");
	}
	
	
	
}
