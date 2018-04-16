package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isa.controller.dto.ReservationDTO;
import com.isa.domain.Reservation;
import com.isa.service.ReservationService;

@RestController
@RequestMapping(value="/reservations")
public class ReservationController {
	@Autowired
	ReservationService service;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO){
		Reservation res = reservationDTO.getReservation();
		Long seatId = reservationDTO.getSeatId();
		Long userId = reservationDTO.getUserId();
		Long projectionId = reservationDTO.getProjectionId();
		Reservation newReservation = service.save(res, projectionId, seatId, userId);
		return new ResponseEntity<Reservation>(newReservation, HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ModelAndView getReservations(@PathVariable Long id, Model model) {
		List<Reservation> reservations = service.getUserReservations(id);
		model.addAttribute("reservations",reservations);
		return new ModelAndView("RegisteredUser/home");
		
		
	}
	@RequestMapping(value = "/lol/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getReservations2(@PathVariable Long id, Model model) {
		List<Reservation> reservations = service.getUserReservations(id);
		model.addAttribute("reservations",reservations);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
		
		
	}

}
