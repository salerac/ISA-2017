package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.domain.Cinema;
@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{
	
		
}
