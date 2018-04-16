package com.isa.controller.dto;

import com.isa.domain.Reservation;

public class ReservationDTO {
	
	private long projectionId;
	
	private Long userId;
	
	private long seatId;	
		
	public Reservation getReservation() {
		Reservation rez = new Reservation();
		return rez;
	}
	public ReservationDTO() {
		super();
	}
	public ReservationDTO(long projectionId, Long userId, long seatId) {
		super();
		this.projectionId = projectionId;
		this.userId = userId;
		this.seatId = seatId;
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
