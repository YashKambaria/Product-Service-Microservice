package com.example.orderService;

import com.example.loadbalancer.config.LoadBalancerGlobalConfig;
import com.example.loadbalancer.config.LoadBalancerProductClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClients(
		defaultConfiguration = LoadBalancerGlobalConfig.class,
		value = {
				@LoadBalancerClient(
				name ="product-service",configuration =LoadBalancerProductClientConfig .class
				)
		}
)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
