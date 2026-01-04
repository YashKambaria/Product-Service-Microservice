package com.example.orderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
	
	@Bean
	public RestClient restClient(ClientHttpRequestInterceptor myCustomInterceptor){
		//in this we configure the restclient and set the interceptor
		
		return RestClient
				.builder()
				.baseUrl("http://localhost:8082/products")
				.build();
				
	}
	
	@Bean
	public ClientHttpRequestInterceptor customRequestInterceptor(){
		//here we return the custom class we made for interceptor
		return new MyCustomRequestInterceptor();
	}
}
