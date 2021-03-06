package com.isa.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.isa.domain.Cinema;
import com.isa.domain.Requisite;
import com.isa.domain.Reservation;
import com.isa.domain.UserAd;
import com.isa.service.CinemaService;
import com.isa.service.RequisiteService;
import com.isa.service.ReservationService;
import com.isa.service.UserAdService;

@ControllerAdvice
public class GlobalModel {
	
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private RequisiteService requisiteService;
	@Autowired
	private UserAdService userAdService;
	
	@ModelAttribute
	public void addCinemas(Model model, HttpSession session) {
		//int userId = 1;
	    //session.setAttribute("userId", userId) ;
		List<Cinema> cinemas = cinemaService.findAll();
		//List<Reservation> reservations = reservationService.getUserReservations(Long.parseLong(session.getAttribute("userId").toString()));
		Collection<Requisite> requisites = requisiteService.findAll();
		Collection<UserAd> userAds = userAdService.findAll();
		model.addAttribute("requisites",requisites);
		model.addAttribute("userAds", userAds);
		model.addAttribute("cinemas",cinemas);
		//model.addAttribute("userId",session.getAttribute("userId"));
	}

}
