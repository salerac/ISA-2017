package com.isa.domain;


public class Offer {
	
	private long userId;
	private int price;
	
	public Offer(long userId, int price) {
		super();
		this.userId = userId;
		this.price = price;
	}

	public Offer() {
		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	

}
