package com.isa.service;

import java.util.Collection;

import com.isa.domain.Requisite;
import com.isa.domain.Reservation;

public interface RequisiteService {
	
	Collection<Requisite> findAll();
	
	Requisite save(Requisite requisite);
	Requisite findOne(Long id);
	Requisite delete(Long id);
	
	Requisite deactivate(long id);

}
