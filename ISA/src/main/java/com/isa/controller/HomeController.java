package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.domain.Reservation;
import com.isa.service.ReservationService;

@Controller
@EnableAutoConfiguration
public class HomeController {
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session) {
		return "login";
	}
	
	@RequestMapping("/registerForm")
	public String register(HttpSession session) {
		return "UnregisteredUsers/registration";
	}
	
	@RequestMapping("/home")
	public String registeredHome(Model model,HttpSession session) {
		List<Reservation> reservations = reservationService.getUserReservations(Long.parseLong(session.getAttribute("userId").toString()));
		for(Reservation r: reservations){
			//r.checkCancel();
		}
		model.addAttribute("reservations",reservations);
		return "RegisteredUser/home";
	}

}
