package com.isa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Repertoire {
	@Id
    @GeneratedValue
    private long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Movie> movies;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Cinema cinema;

	public Movie getMovie(int i) {
		return movies.get(i);
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	

}
