package com.isa.domain;


import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Projection {
	@Id
	@GeneratedValue
	private long id;
	
	private Date date;
	
	@ManyToOne
	private ShowRoom showRoom;
	
	@OneToMany
	private List<Seat> seats;
	
	private double price;
	
	/*@ManyToOne
	private Movie movie;
	
*/	@OneToMany
	@JsonBackReference
	private List<Reservation> reservations;
	

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		
	}
	public long getId() {
		return id;
	}
	

	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}


	public ShowRoom getShowRoom() {
		return showRoom;
	}



	public void setShowRoom(ShowRoom showRoom) {
		this.showRoom = showRoom;
	}



	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*public Movie getMovie() {
		return movie;
	}*/

	/*public void setMovie(Movie movie) {
		this.movie = movie;
	}*/
	
	

}
