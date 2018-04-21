package com.isa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.domain.Offer;
import com.isa.domain.Reservation;
import com.isa.domain.UserAd;
import com.isa.service.ReservationService;
import com.isa.service.UserAdService;

@Controller
@EnableAutoConfiguration
public class HomeController {
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserAdService userAdService;
	
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
		List<UserAd> userAds = userAdService.findByCreatorId(Long.parseLong(session.getAttribute("userId").toString()));
		
		ArrayList<Offer> offers = new ArrayList<Offer>();
		for(UserAd ad: userAds) {
			Set<Offer> offers1 = ad.getOffers();
			for(Offer o: offers1) {
				offers.add(o);
			}
		}
		
		for(Reservation r: reservations){
			//r.checkCancel();
		}
		
		model.addAttribute("offers",offers);
		model.addAttribute("reservations",reservations);
		return "RegisteredUser/home";
		
	}
}
