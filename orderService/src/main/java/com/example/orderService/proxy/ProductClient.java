package com.example.orderService.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",
		url = "${feign.client.product-service.url}"
)
public interface ProductClient {

	@GetMapping("/products/{id}")
	String getProductById(@PathVariable("id") String id);
}
