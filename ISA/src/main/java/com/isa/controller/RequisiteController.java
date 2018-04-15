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

import com.isa.domain.Requisite;

import com.isa.service.RequisiteService;

@RestController
@RequestMapping(value="/requisite")
public class RequisiteController {
	@Autowired
	private RequisiteService requisiteService;
	
	@RequestMapping(value="getRequisites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Requisite>> getRequisites(){
		Collection<Requisite> requisites = requisiteService.findAll();
		return new ResponseEntity<Collection<Requisite>>(requisites,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Requisite> deleteRequisite (@PathVariable Long id){
		Requisite deleted = requisiteService.delete(id);
		return new ResponseEntity<>(deleted,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Requisite> getRequisite(@PathVariable Long id){
		Requisite requisite = requisiteService.findOne(id);
		return new ResponseEntity<>(requisite, HttpStatus.OK);
	}
	
	@RequestMapping(method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Requisite> addNewRequisite(@RequestBody Requisite requisite){
		Requisite newRequisite = requisiteService.save(requisite);
		return new ResponseEntity<Requisite>(newRequisite, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Requisite> editRequisite(@PathVariable Long id,@RequestBody Requisite requisite){
		requisite.setId(id);
		Requisite editedRequisite = requisiteService.save(requisite);
		return new ResponseEntity<>(editedRequisite, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<Requisite> reserveRequisite(@PathVariable Long id, @PathVariable Long userId){
		Requisite requisite = requisiteService.findOne(id);
		requisite.setReserved(userId);
		Requisite reservedRequisite = requisiteService.save(requisite);
		return new ResponseEntity<>(reservedRequisite, HttpStatus.OK);
	}
	

}
