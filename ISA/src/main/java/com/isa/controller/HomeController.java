package com.isa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class HomeController {
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session) {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(HttpSession session) {
		return "UnregisteredUsers/registration";
	}
	
	@RequestMapping("/home")
	public String registeredHome(HttpSession session) {
		return "RegisteredUser/home";
	}

}
