package com.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.domain.User;
import com.isa.service.UserService;



@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody User user) throws Exception{
		User registeredUser = service.save(user);
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
}
	
	@RequestMapping(value="/loadUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		Collection<User> users = service.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
	
}
