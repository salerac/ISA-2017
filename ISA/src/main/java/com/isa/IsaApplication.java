package com.isa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.isa.domain.User;
import com.isa.repository.UserRepository;

@SpringBootApplication
public class IsaApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);

	}

}
