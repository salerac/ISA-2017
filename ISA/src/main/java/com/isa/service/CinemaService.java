package com.isa.service;

import java.util.List;

import com.isa.domain.Cinema;

public interface CinemaService {
	
	List<Cinema> findAll();

	Cinema save(Cinema cinema);

	Cinema findOne(Long id);
	
	void deleteAll();

}
