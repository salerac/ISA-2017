package com.isa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Indexed
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cinema {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Field
	private String name;
	
	private String adress;
	
	private String city;
	
	private String description;
	@OneToOne
	private Repertoire repertoire;
	@NotNull
	private boolean isCinema;
	@OneToMany
	private List<ShowRoom> sale;
	
	
	public Repertoire getRepertoire() {
		return repertoire;
	}
	public void setRepertoire(Repertoire repertoire) {
		this.repertoire = repertoire;
	}
	public List<ShowRoom> getSale() {
		return sale;
	}
	public void setSale(List<ShowRoom> sale) {
		this.sale = sale;
	}
	public Cinema(Long id, String name, String adress, String description) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
	}
	public Cinema() {}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public boolean isCinema() {
		return isCinema;
	}
	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
