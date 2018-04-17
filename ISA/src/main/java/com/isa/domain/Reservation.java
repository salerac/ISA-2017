package com.isa.domain;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@ManyToOne
	private Movie movie;
	@ManyToOne
	private Cinema cinema;
	@OneToOne
	private Seat reservedSeat;
	
	private boolean active;

	public boolean checkCancel() {
		Date todayDate = new Date();
		if((todayDate.toInstant().isBefore(projection.getDate().toInstant().minus(30,ChronoUnit.MINUTES))) && !(todayDate.after(projection.getDate()))) {
			System.out.println("Aktivna");
			return true;
		}
			
		else {
			this.active = false;
			return false;
		}
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Reservation() {}
	
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public Seat getReservedSeat() {
		return reservedSeat;
	}

	public void setReservedSeat(Seat reservedSeat) {
		this.reservedSeat = reservedSeat;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

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
