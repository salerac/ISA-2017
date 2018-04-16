package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.domain.Movie;



public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByRepertoireId(long id);
}
