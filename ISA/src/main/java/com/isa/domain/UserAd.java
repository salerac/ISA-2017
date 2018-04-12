package com.isa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="UserAd")
public class UserAd {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Name", columnDefinition="VARCHAR(80)")
	private String nameAd;

	@Column(name="Description", columnDefinition="VARCHAR(150)")
	private String descriptionAd;
	
	@Column(name="Date", columnDefinition="DATE")
	private String date;
	
	@Column(name="Picture", columnDefinition="VARCHAR(200)")
	private String imageAd= "No Picture";
	
	@Column(name="Approval", columnDefinition="BOOLEAN")
	private Boolean aproved=false;
	

	public UserAd(String nameAd, String descriptionAd, String date, String imageAd, Boolean aproved) {
		super();
		this.nameAd = nameAd;
		this.descriptionAd = descriptionAd;
		this.date = date;
		this.imageAd = imageAd;
		this.aproved = aproved;
	}

	
	public UserAd () {
		
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNameAd() {
		return nameAd;
	}


	public void setNameAd(String nameAd) {
		this.nameAd = nameAd;
	}

	public String getDescriptionAd() {
		return descriptionAd;
	}

	public void setDescriptionAd(String descriptionAd) {
		this.descriptionAd = descriptionAd;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getImageAd() {
		return imageAd;
	}


	public void setImageAd(String imageAd) {
		this.imageAd = imageAd;
	}


	public Boolean getAproved() {
		return aproved;
	}


	public void setAproved(Boolean aproved) {
		this.aproved = aproved;
	}

	
}

