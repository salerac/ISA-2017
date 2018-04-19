package com.isa.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.isa.domain.Projection;
import com.isa.domain.Seat;
import com.isa.service.ProjectionService;

@Controller
@RequestMapping("/projections")
public class ProjectionController {
	@Autowired
	private ProjectionService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView findAvailableSeats(@PathVariable Long id, Model model){
		
		List<Seat> seats = service.findReservedSeats(id);
		Projection projection = service.findOne(id);
		int length = projection.getShowRoom().getLength();
		int width =  projection.getShowRoom().getWidth();
		
		model.addAttribute("seats", seats);
		model.addAttribute("projection", projection);
		model.addAttribute("length", length);
		model.addAttribute("width",width);
		
		return new ModelAndView("RegisteredUser/seats");
		
		
	}
	

		

}
