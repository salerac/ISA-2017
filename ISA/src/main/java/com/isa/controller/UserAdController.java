package com.isa.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.domain.Offer;

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

}