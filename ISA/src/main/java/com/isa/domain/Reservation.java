package com.isa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User reserver;
	@ManyToOne
	private Projection projection;
	@OneToMany
	private List<Seat> reservedSeats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReserver() {
		return reserver;
	}

	public void setReserver(User reserver) {
		this.reserver = reserver;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public List<Seat> getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(List<Seat> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
	
	

}
