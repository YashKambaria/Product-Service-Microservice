package com.example.orderService.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MyCustomRequestInterceptor implements ClientHttpRequestInterceptor {
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		
		
		//here we have added the header (custom header) which we can use ahead
		request.getHeaders().add("MyCustomX","myvalue");
		System.out.println("Interceptor added header to outgoing request: " + request.getHeaders());
		
		return execution.execute(request,body);
	}
}
