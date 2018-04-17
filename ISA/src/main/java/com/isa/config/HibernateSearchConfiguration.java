package com.isa.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isa.service.CinemaSearchService;

@EnableAutoConfiguration 
@Configuration
public class HibernateSearchConfiguration {
	
	@Autowired
	private EntityManager bentityManager;

	@Bean
	CinemaSearchService hibernateSearchService() {
		CinemaSearchService hibernateSearchService = new CinemaSearchService(bentityManager);
		hibernateSearchService.initializeHibernateSearch();
		return hibernateSearchService;
	}
	
	

}
