package com.isa.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.isa.domain.Cinema;
import com.isa.domain.Movie;
import com.isa.domain.Projection;
import com.isa.service.CinemaSearchService;
import com.isa.service.CinemaService;

@Controller
@RequestMapping("/cinemas")
public class CinemaController {
	
	@Autowired
	private CinemaService service;
	@Autowired
	private CinemaSearchService searchService;

	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cinema>> getAll(Model model, HttpSession session) throws IOException{
			List<Cinema> cinemas = service.findAll();
			model.addAttribute("cinemas",cinemas);
			model.addAttribute("userId",session.getAttribute("userId"));
			return new ResponseEntity<>(cinemas, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView getCinema(@PathVariable Long id, Model model){
			Cinema cinema = service.findOne(id);
			List<Movie> movies = cinema.getRepertoire().getMovies();
			model.addAttribute("cinema", cinema);
			model.addAttribute("movies", movies);
			return new ModelAndView("RegisteredUser/Cinemas");
		
	}
	
	@RequestMapping(method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cinema> addCinema(@RequestBody Cinema cinema){
			service.save(cinema);
			return new ResponseEntity<>(cinema,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ResponseEntity<Cinema> updateCinema(@RequestParam Long id,@RequestBody Cinema cinema){
			cinema.setId(id);
			Cinema updatedCinema = service.save(cinema);
			return new ResponseEntity<>(updatedCinema, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Cinema> deleteAll(){
			service.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/search/", method= RequestMethod.GET)
	@ResponseBody
	public Object search(@RequestParam(value="search") String q, Model model, HttpSession session) {
		
		List<Cinema> cinemas = null;
		cinemas = searchService.fuzzySearch(q);
		ArrayList<Cinema> onlyCinemas = new ArrayList<Cinema>();
		if(!cinemas.isEmpty()) {
		for(int i = 0; i< cinemas.size(); i++) {
			if(cinemas.get(i).isCinema())
				onlyCinemas.add(cinemas.get(i));		
		}
		if(!onlyCinemas.isEmpty()) {	
		session.setAttribute("search",onlyCinemas);
		return new ResponseEntity<>(onlyCinemas,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

}
