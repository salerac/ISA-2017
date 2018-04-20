package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Movie;
import com.isa.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository repository;
	
	public Movie findOne(Long id) {
		return repository.getOne(id);
	}

}
