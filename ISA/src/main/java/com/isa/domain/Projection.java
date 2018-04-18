package com.isa.domain;


import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Projection {
	@Id
	@GeneratedValue
	private long id;
	
	private Date date;
	
	
	//private ShowRoom showRoom;
	
	@OneToMany
	private List<Seat> seats;
	
	private double price;
	
	/*@ManyToOne
	private Movie movie;
	
*/
	
	
	public long getId() {
		return id;
	}

	/*public ShowRoom getShowRoom() {
		return showRoom;
	}



	public void setShowRoom(ShowRoom showRoom) {
		this.showRoom = showRoom;
	}
*/


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
