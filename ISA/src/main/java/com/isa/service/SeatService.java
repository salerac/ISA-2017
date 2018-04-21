package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Seat;
import com.isa.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository repository;
	
	public Seat findOne(Long id) {
		return repository.getOne(id);
	}

}
