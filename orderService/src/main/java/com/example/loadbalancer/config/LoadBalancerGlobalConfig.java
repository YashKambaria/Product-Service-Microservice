package com.example.loadbalancer.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConditionalOnMissingBean(ReactorLoadBalancer.class)
// At first the child loadbalancing config will be executed.
// and after that the default one so we do not want to override the config done on child by global or default config. so we used
// conditionalOnMissingBean annotation to add the config only if
// the loadbalancing is missing
public class LoadBalancerGlobalConfig {


	@Bean
	public ReactorLoadBalancer<ServiceInstance> defaultLoadBalancer(
			Environment environment,
			LoadBalancerClientFactory factory
	){
		String serviceId = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		
		return new RoundRobinLoadBalancer(
				factory.getLazyProvider(serviceId,ServiceInstanceListSupplier.class),
				serviceId
		);
	}

}
