package com.example.orderService.entity;

public class Product {
	
	private String id;
	private String name;
	private int price;
	
	private int productCount;
	
	public Product(String name, String id, int price, int productCount) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.productCount = productCount;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getProductCount() {
		return productCount;
	}
	
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
}
