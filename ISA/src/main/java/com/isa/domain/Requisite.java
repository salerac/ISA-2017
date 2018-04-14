package com.isa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Requisite")
public class Requisite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Name", columnDefinition="VARCHAR(80)")
	private String nameReq;

	@Column(name="Description", columnDefinition="VARCHAR(150)")
	private String descriptionReq;
	
	@Column(name="Picture", columnDefinition="VARCHAR(200)")
	private String image= "No Picture";
	
	@Column(name="Price", columnDefinition="DECIMAL(40,2)")
	private float price;
	
	@Column(name="Reserved", columnDefinition="NUMERIC")
	private long reserved=0;

	
	public Requisite(String nameReq, String descriptionReq, String image, float price, long reserved) {
		super();
		this.nameReq = nameReq;
		this.descriptionReq = descriptionReq;
		this.image = image;
		this.price = price;
		this.reserved = reserved;
	}
	
	public Requisite() {
	}

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNameReq() {
		return nameReq;
	}

	public void setNameReq(String nameReq) {
		this.nameReq = nameReq;
	}


	public String getDescriptionReq() {
		return descriptionReq;
	}


	public void setDescriptionReq(String descriptionReq) {
		this.descriptionReq = descriptionReq;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}

	public long getReserved() {
		return reserved;
	}

	public void setReserved(long reserved) {
		this.reserved = reserved;
	}
	
	
}






