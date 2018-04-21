package com.isa.service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Cinema;
import com.isa.domain.Movie;
import com.isa.domain.Projection;
import com.isa.domain.Reservation;
import com.isa.domain.Seat;
import com.isa.domain.User;
import com.isa.repository.CinemaRepository;
import com.isa.repository.MovieRepository;
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
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ProjectionService projectionService;
	
	public Reservation save(Reservation reservation, Long projectionId, Long seatId, Long userId, Long cinemaId, Long movieId) {
		Projection projection = projectionRepository.getOne(projectionId);
		Seat seat = seatRepository.getOne(seatId);
		User user = userRepository.getOne(userId);
		Cinema cinema = cinemaRepository.getOne(cinemaId);
		Movie movie = movieRepository.getOne(movieId);
		
		reservation.setProjection(projection);
		//reservation.setMovie(projection.getMovie());
		reservation.setReservedSeat(seat);
		reservation.setReserver(user);
		reservation.setCinema(cinema);
		reservation.setMovie(movie);
		reservation.setActive(true);
		
		List<Seat> reservedSeats = projectionService.findReservedSeats(projectionId);
		
		if(reservedSeats.contains(reservation.getReservedSeat())) {
			
			return null;
		}
		Reservation returnRes = reservationRepository.save(reservation);
		projection.addReservation(reservation);
		projectionRepository.save(projection);
		return returnRes;
		
	}
	
	public Reservation delete(long id) {
		Reservation reservation = reservationRepository.getOne(id);
		if(reservation!=null) {
			reservationRepository.delete(reservation);
			return reservation;
		}
		return null;
	}
	public Reservation deactivate(long id) {
		System.out.println("Usao");
		Reservation reservation = reservationRepository.getOne(id);
		Date date = reservation.getProjection().getDate();
		Date todayDate = new Date();
		if(reservation != null) {
		if(!date.toInstant().isBefore(todayDate.toInstant().minus(30,ChronoUnit.MINUTES))) {		
			reservation.setActive(false);
		    reservationRepository.save(reservation);
		    return reservation;
		}
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
