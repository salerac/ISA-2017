package com.isa.service;

import java.util.Collection;

import com.isa.domain.Requisite;

public interface RequisiteService {
	
	Collection<Requisite> findAll();
	
	Requisite save(Requisite reguisite);
	Requisite findOne(Long id);
	Requisite delete(Long id);

}