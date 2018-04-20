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

import com.isa.domain.Cinema;
import com.isa.domain.Movie;
import com.isa.domain.Projection;
import com.isa.domain.Seat;
import com.isa.service.CinemaService;
import com.isa.service.MovieService;
import com.isa.service.ProjectionService;
import com.isa.service.ShowRoomService;

@Controller
@RequestMapping("/projections")
public class ProjectionController {
	@Autowired
	private ProjectionService service;
	@Autowired
	private ShowRoomService showRoomService;
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/{id}/{cinemaId}/{movieId}", method=RequestMethod.GET)
	public ModelAndView findAvailableSeats(@PathVariable("id") Long id,@PathVariable("cinemaId") Long cinemaId,@PathVariable("movieId") Long movieId,  Model model){
		
		Cinema cinema = cinemaService.findOne(cinemaId);
		Movie movie = movieService.findOne(movieId);
		List<Seat> seats = service.findReservedSeats(id);
		Projection projection = service.findOne(id);
		showRoomService.makeSeats(projection.getShowRoom().getId());
		List<Seat> showRoomSeats = projection.getShowRoom().getSeats();
		
		int length = projection.getShowRoom().getLength();
		int width =  projection.getShowRoom().getWidth();
		
		model.addAttribute("seats", seats);
		model.addAttribute("projection", projection);
		model.addAttribute("length", length);
		model.addAttribute("width",width);
		model.addAttribute("cinema",cinema);
		model.addAttribute("movie",movie);
		model.addAttribute("showRoomSeats",showRoomSeats);
		
		return new ModelAndView("RegisteredUser/seats");
		
		
	}
	

		

}
