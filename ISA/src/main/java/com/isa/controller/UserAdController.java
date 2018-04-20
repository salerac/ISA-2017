package com.isa.controller;

import java.io.IOException;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isa.domain.Offer;
import com.isa.domain.Requisite;
import com.isa.domain.UserAd;
import com.isa.service.UserAdService;

@RestController
@RequestMapping(value= "/userAd")
public class UserAdController {
	
	@Autowired 
	private UserAdService userAdService;
	
	@RequestMapping(value="getUserAds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UserAd>> getUserAds(){
		Collection<UserAd> userAds = userAdService.findAll();
		return new ResponseEntity<Collection<UserAd>>(userAds,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getAll(Model model) throws IOException{
			Collection<UserAd> userAds = userAdService.findAll();
			model.addAttribute("userAds",userAds);
			return new ModelAndView("RegisteredUser/home");
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<UserAd> disaproveUserAd (@PathVariable Long id){
		UserAd disaprovedUserAd = userAdService.delete(id);
		return new ResponseEntity<>(disaprovedUserAd,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserAd> aproveUserAd(@PathVariable Long id){
		UserAd userAd = userAdService.findOne(id);
		userAd.setAproved(true);
		userAdService.save(userAd, true);
		return new ResponseEntity<>(userAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserAd/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserAd> getUserAd(@PathVariable long id){
		UserAd userAd = userAdService.findOne(id);
		return new ResponseEntity<>(userAd,HttpStatus.OK);
	}
	
	@RequestMapping(method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAd> addUserAd(@RequestBody UserAd userAd){
		System.out.println(userAd.getDate());
		
		UserAd newUserAd = userAdService.save(userAd, false);
		return new ResponseEntity<>(newUserAd,HttpStatus.OK);
}
	
	@RequestMapping(value = "/makeOffer/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAd> makeOffer(@RequestBody Offer offer,@PathVariable Long id){
		UserAd userAd = userAdService.findOne(id);
		userAd.addOffer(offer);
		UserAd newUserAd = userAdService.save(userAd,true);
		return new ResponseEntity<>(newUserAd,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/aprove", method=RequestMethod.GET)
	public ResponseEntity<Long> aprove(@RequestParam(value="id") Long id) {
		UserAd userAd = userAdService.aprove(id);
		if(userAd != null)
		return new ResponseEntity<>(id,HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

}
