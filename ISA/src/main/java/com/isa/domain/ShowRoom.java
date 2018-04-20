package com.isa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class ShowRoom {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int number;
	
	private int length;
	
	private int width;
	
	@OneToMany
	private List<Seat> seats;
	
	public List<Seat> getSeats() {
		return seats;
	}


	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public ShowRoom() {
		super();
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	

	

}
