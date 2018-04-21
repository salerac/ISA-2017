package com.isa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.isa.controller.dto.ReservationDTO;
import com.isa.domain.Reservation;
import com.isa.domain.User;
import com.isa.service.EmailService;
import com.isa.service.ReservationService;
import com.isa.service.SeatService;
import com.isa.service.UserService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/reservations")
public class ReservationController {
	@Autowired
	private ReservationService service;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SeatService seatService;
	
	private Reservation newReservation;
	private User user;
	private ArrayList<Integer> seatNumbers; 
	
	
	@RequestMapping(value="/{seats}", method=RequestMethod.POST)
	public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable Long []seats){
		seatNumbers = new ArrayList<Integer>();
		
		for(Long i: seats) {
		seatNumbers.add(seatService.findOne(i).getNumber());	
			
		Reservation res = reservationDTO.getReservation();
		Long seatId = i;
		Long userId = reservationDTO.getUserId();
		Long projectionId = reservationDTO.getProjectionId();
		Long cinemaId = reservationDTO.getCinemaId();
		Long movieId = reservationDTO.getMovieId();
		user = userService.findOne(userId);
		
		newReservation = service.save(res, projectionId, seatId, userId,cinemaId,movieId);
		if(newReservation == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		}
		SimpleMailMessage reservationEmail = new SimpleMailMessage();
		reservationEmail.setTo(user.getEmail());
		reservationEmail.setSubject("Your reservation");
		reservationEmail.setText("Your reservation information:\n"
				+ "Location: " + newReservation.getCinema().getName() + "\n"
				+ "Title: " + newReservation.getMovie().getName() + "\n"
				+ "Date: " + newReservation.getProjection().getDate() + "\n"
				+ "Room: " + newReservation.getProjection().getShowRoom().getNumber() + "\n"
				+ "Seats: " + Arrays.toString(seatNumbers.toArray()) + "\n");
		reservationEmail.setFrom("noreply@domain.com");
		
		emailService.sendEmail(reservationEmail);
		return new ResponseEntity<Reservation>(newReservation, HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ModelAndView getReservations(@PathVariable Long id, Model model) {
		List<Reservation> reservations = service.getUserReservations(id);
		model.addAttribute("reservations",reservations);
		return new ModelAndView("RegisteredUser/home");
		
		
	}
	@RequestMapping(value = "/deactivate", method=RequestMethod.GET)
	public ResponseEntity<Long> deactivate(@RequestParam(value="id") Long id) {
		Reservation reservation = service.deactivate(id);
		if(reservation != null)
		return new ResponseEntity<>(id,HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
