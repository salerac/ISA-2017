package com.isa.controller;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.isa.controller.dto.LoginDTO;
import com.isa.domain.Privilege;
import com.isa.domain.Requisite;
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
	public Object login(@RequestBody LoginDTO loginDTO, HttpSession session){
		User user = service.findByEmail(loginDTO.getEmail());
		
		if(user == null){
			System.out.print("user null");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		else{
			if(loginDTO.getPassword().equals(user.getPassword())){
			if(!user.isEnabled()) {
				System.out.print("nije validan");
				return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
			}
			
			else {
			     System.out.println("RADI");
			     session.setAttribute("loggedUser", user);
			     session.setAttribute("userId", user.getId());
			     
			     return new ResponseEntity<>(HttpStatus.OK);
			}
			}
			else {
				System.out.print("nisu iste lozinke");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}
	@RequestMapping(value="/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody User user, HttpServletRequest request) throws Exception{
		User registeredUser = service.findByEmail(user.getEmail());
		System.out.println("USAO JE");
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
	public ModelAndView processConfirmationForm(@RequestParam Map requestParams) throws Exception{
		User user = service.findByConfirmationToken(requestParams.get("token").toString());
		user.setEnabled(true);
		User savedUser = service.save(user);
		return new ModelAndView("/login");
	}
	
	@RequestMapping(value="/edit/{id}", method= RequestMethod.PUT)
	public ResponseEntity<User> editUser(@RequestBody User newUser, @PathVariable(value="id") Long id) throws Exception{
		User user = service.findOne(id);
		user.setName(newUser.getName());
		user.setSurname(newUser.getSurname());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		user.setCity(newUser.getCity());
		user.setPhoneNumber(newUser.getPhoneNumber());
		service.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
		
	}	
	@RequestMapping("/signout")
	public ModelAndView signout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/loadUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(HttpSession session){
		
		Collection<User> users = service.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/addUser", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user) throws Exception{
		user.setEnabled(true);
		user.setConfirmationToken(null);
		
		User newUser = service.save(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit1/{id}", method= RequestMethod.PUT)
	public ResponseEntity<User> editUser1(@RequestBody User newUser, @PathVariable(value="id") Long id) throws Exception{
		User user = service.findOne(id);
		user.getName();
		user.getSurname();
		user.getEmail();
		user.setPassword(newUser.getPassword());
		user.getCity();
		user.getPhoneNumber();
		service.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
