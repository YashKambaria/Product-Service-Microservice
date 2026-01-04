package com.example.orderService.controller;


import com.example.orderService.entity.OrderRequest;
import com.example.orderService.entity.Product;
import com.example.orderService.proxy.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final RestClient restClient;
	
	public OrderController(RestClient restClient) {
		this.restClient = restClient;
	}
	
	@Autowired
	public ProductClient productClient;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable String id){
		String uri=id;
		
		String body = restClient.get()
				.uri(uri)
				.retrieve()
				.body(String.class);
		
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/feign/{id}")
	public ResponseEntity<String> getOrder2(@PathVariable String id){
		String productById = productClient.getProductById(id);
		return ResponseEntity.ok(productById);
	}
	
	@PostMapping
	public ResponseEntity<?> placeOrder(
			@RequestBody Product product
	){
		
		try {
			ResponseEntity<String> response = restClient.post()
					.contentType(MediaType.APPLICATION_JSON)
					.body(product)
					.retrieve()
					.toEntity(String.class);
			
			if(response.getStatusCode().is2xxSuccessful()) {
				return ResponseEntity.ok(response.getBody());
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		}
		catch (Exception e){
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/place-order")
	public ResponseEntity<?> placeOrder(
			@RequestBody OrderRequest orderRequest
			)
	{
	
		String uri="/price/"+orderRequest.getProductId();
		
		ResponseEntity<Integer> body = restClient.get()
				.uri(uri)
				.retrieve()
				.toEntity(Integer.class);
		
		if(body.getStatusCode().is2xxSuccessful()){
			int totalPrice=orderRequest.getQuantity()*body.getBody();
			String description="Thanks for placing the order the bill is of "+totalPrice;
			
			return ResponseEntity.ok(description);
		}
		else{
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
}
