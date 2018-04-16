package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.domain.Projection;

public interface ProjectionRepository extends JpaRepository<Projection, Long> {
	
	public List<Projection> findByMovieId(long id);
}
