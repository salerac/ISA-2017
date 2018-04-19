package com.isa.service;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Requisite;
import com.isa.domain.Reservation;
import com.isa.repository.RequisiteRepository;

@Service 
public class RequisiteServiceImpl implements RequisiteService {
	
	@Autowired
	private RequisiteRepository requisiteRepository;

	@Override
	public Collection<Requisite> findAll() {
		// TODO Auto-generated method stub
		return requisiteRepository.findAll();
	}

	@Override
	public Requisite save(Requisite requisite) {
		// TODO Auto-generated method stub
		return requisiteRepository.save(requisite);
	}

	@Override
	public Requisite findOne(Long id) {
		return requisiteRepository.getOne(id);
	}

	@Override
	public Requisite delete(Long id) {
		// TODO Auto-generated method stub
		Requisite requisite = requisiteRepository.getOne(id);
		if(requisite == null) {
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite");
		}
		requisiteRepository.delete(requisite);
		return requisite;
		
		
	}
	public Requisite deactivate(long id) {
		System.out.println("Usao");
		Requisite requisite = requisiteRepository.getOne(id);
		
		if(requisite != null) {
		
		}
		return null;
	}

}
