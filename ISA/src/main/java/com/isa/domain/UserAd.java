package com.isa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

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
	
	/*
	@Column(name="Approval", columnDefinition="BOOLEAN")
	private Boolean aproved=false;
	*/
	@Column(name="Aproved", columnDefinition="VARCHAR(80)")
	private boolean aproved;
	
	@Column(name="CreatedBy", columnDefinition="NUMERIC")
	private long creatorId;
	
	
	@ElementCollection
	@CollectionTable(name = "Offers",joinColumns = @JoinColumn(name = "userAd_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "userId", column = @Column(name = "InterstedParty")),
        @AttributeOverride(name = "price", column = @Column(name = "Price")),
})
	
	
	
	Set<Offer> offers = new HashSet<Offer>();

	

	
	public UserAd(String nameAd, String descriptionAd, String date, String imageAd, boolean aproved, long creatorId) {
		super();
		this.nameAd = nameAd;
		this.descriptionAd = descriptionAd;
		this.date = date;
		this.imageAd = imageAd;
		this.aproved = aproved;
		this.creatorId = creatorId;
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


	

	public long getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}


	public Set<Offer> getOffers() {
		return offers;
	}


	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	
	public void addOffer(Offer offer) {
		offers.add(offer);
	}


	public boolean isAproved() {
		return aproved;
	}


	public void setAproved(boolean aproved) {
		this.aproved = aproved;
	}
	
	

	
}

