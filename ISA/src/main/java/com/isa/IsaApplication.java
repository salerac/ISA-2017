package com.isa;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.domain.User;
import com.isa.repository.UserRepository;

@SpringBootApplication
public class IsaApplication {
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
	    Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
	    b.mixIn(Object.class, IgnoreHibernatePropertiesInJackson.class);
	    return b;
	}


	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private abstract class IgnoreHibernatePropertiesInJackson{ }


	

	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);

	}

}
