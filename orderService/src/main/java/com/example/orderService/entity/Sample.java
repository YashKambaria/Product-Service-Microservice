package com.example.orderService.entity;

public class Sample {
	
	private String id;
	private String env;
	
	public Sample(String env, String id) {
		this.env = env;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEnv() {
		return env;
	}
	
	public void setEnv(String env) {
		this.env = env;
	}
}
