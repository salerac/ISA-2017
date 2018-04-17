package com.isa.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.isa.domain.Cinema;
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
	public ModelAndView getAll(Model model, HttpSession session) throws IOException{
			List<Cinema> cinemas = service.findAll();
			model.addAttribute("cinemas",cinemas);
			model.addAttribute("userId",session.getAttribute("userId"));
			return new ModelAndView("RegisteredUser/home");
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
	public ResponseEntity<List<Cinema>> search(@RequestParam(value="search") String q, Model model) {
		List<Cinema> cinemas = null;
		cinemas = searchService.fuzzySearch(q);
		if(!cinemas.isEmpty()) {
		model.addAttribute("search",cinemas);
		return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

}
