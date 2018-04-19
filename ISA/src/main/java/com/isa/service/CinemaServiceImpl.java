package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Cinema;
import com.isa.repository.CinemaRepository;
@Service
public class CinemaServiceImpl implements CinemaService{ 

	@Autowired
	CinemaRepository repository;

	@Override
	public List<Cinema> findAll() {
		return repository.findAll();
	}

	@Override
	public Cinema save(Cinema cinema) {
		return repository.save(cinema);
	}

	@Override
	public Cinema findOne(Long id) {
		return (Cinema) repository.getOne(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
		
	}
	

}
