package com.isa.controller.dto;

import com.isa.domain.Reservation;

public class ReservationDTO {
	
	private long projectionId;
	
	private Long userId;
	
	private long seatId;
	
	private Long cinemaId;
	
	private Long movieId;
		
	
	public Reservation getReservation() {
		Reservation rez = new Reservation();
		return rez;
	}
	public ReservationDTO() {
		super();
	}
	public ReservationDTO(long projectionId, Long userId, long seatId, Long cinemaId, Long movieId) {
		super();
		this.projectionId = projectionId;
		this.userId = userId;
		this.seatId = seatId;
		this.cinemaId = cinemaId;
		this.movieId= movieId;
	}
	
	public Long getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public long getProjectionId() {
		return projectionId;
	}
	public void setProjectionId(long projectionId) {
		this.projectionId = projectionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public long getSeatId() {
		return seatId;
	}
	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}
	
	
}
