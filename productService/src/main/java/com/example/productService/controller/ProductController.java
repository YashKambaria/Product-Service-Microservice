package com.example.productService.controller;


import com.example.productService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	HashMap<String, Product> data=new HashMap<>();
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/{id}")
	public String getProduct(@PathVariable String id){
		String port=environment.getProperty("local.server.port");
		return port;
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
	
	@PatchMapping("/update/{id}")
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
		if(product.getName()!=null && !product.getName().isEmpty() && !product.getName().trim().isBlank()
		&& !product.getName().equals(old.getName())){
			old.setName(product.getName());
		}
		
		return ResponseEntity.ok("Product updated succesfully");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(
			@PathVariable String id
	){
		if(data.containsKey(id)) {
			data.remove(id);
		   return ResponseEntity.noContent().build();
		}
		else{
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
