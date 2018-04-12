package com.isa.controller;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.controller.dto.LoginDTO;
import com.isa.domain.Privilege;
import com.isa.domain.User;
import com.isa.service.EmailService;
import com.isa.service.UserService;



@RestController
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping(value="/signin", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO){
		User user = service.findByEmail(loginDTO.getEmail());
		if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else{
			if(loginDTO.getPassword().equals(user.getPassword())){
			if(!user.isEnabled())
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
			else {
				service.setActiveUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			}
			else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody User user, HttpServletRequest request) throws Exception{
		User registeredUser = service.findByEmail(user.getEmail());
		
		if(registeredUser != null){
			String response = "Korisnik sa datim email-om vec postoji.";
			return new ResponseEntity(response,HttpStatus.CONFLICT);	
		}
		else{
			user.setPrivilege(Privilege.REGISTERED_USER);
			user.setEnabled(false);
			user.setConfirmationToken(UUID.randomUUID().toString());
			User returnUser = service.save(user);
			String appUrl = request.getScheme() + "://" + request.getServerName();
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + ":8080/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			return new ResponseEntity(returnUser,HttpStatus.OK);
		}
		
}
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ResponseEntity<User> processConfirmationForm(@RequestParam Map requestParams) throws Exception{
		User user = service.findByConfirmationToken(requestParams.get("token").toString());
		user.setEnabled(true);
		User savedUser = service.save(user);
		return new ResponseEntity(savedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value="/loadUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		Collection<User> users = service.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
	
}
