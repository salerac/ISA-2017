package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Projection;
import com.isa.domain.Reservation;
import com.isa.domain.Seat;
import com.isa.domain.User;
import com.isa.repository.CinemaRepository;
import com.isa.repository.ProjectionRepository;
import com.isa.repository.ReservationRepository;
import com.isa.repository.SeatRepository;
import com.isa.repository.UserRepository;
@Service
public class ReservationService {
	
	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	public Reservation save(Reservation reservation, Long projectionId, Long seatId, Long userId) {
		Projection projection = projectionRepository.getOne(projectionId);
		Seat seat = seatRepository.getOne(seatId);
		User user = userRepository.getOne(userId);
		
		reservation.setProjection(projection);
		reservation.setMovie(projection.getMovie());
		reservation.setReservedSeat(seat);
		reservation.setReserver(user);
		
		return reservationRepository.save(reservation);
		
	}
	
	public Reservation delete(long id) {
		Reservation reservation = reservationRepository.getOne(id);
		if(reservation!=null) {
			reservationRepository.delete(reservation);
			return reservation;
		}
		return null;
	}
	
	public List<Reservation> getUserReservations(long id){
		return reservationRepository.findByReserverId(id);
	}
	public List<Reservation> getProjectionReservations(long id){
		return reservationRepository.findByProjectionId(id);
	}

}
