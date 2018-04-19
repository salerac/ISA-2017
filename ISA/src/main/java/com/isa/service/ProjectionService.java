package com.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Projection;
import com.isa.domain.Seat;
import com.isa.repository.ProjectionRepository;

@Service
public class ProjectionService {
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	public List<Seat> findReservedSeats(Long id){
		Projection proj = projectionRepository.getOne(id);
		ArrayList<Seat> reservedSeats = new ArrayList<Seat>();
		for(int i = 0; i<proj.getReservations().size(); i++) {
			reservedSeats.add(proj.getReservations().get(i).getReservedSeat());
		}
		
		return reservedSeats;
	}
	
	public List<Seat> findAllSeats(Long id){
		Projection proj = projectionRepository.getOne(id);
		return proj.getSeats();
	}
	
	public List<Seat> findAvailableSeats(Long id){
		List<Seat> allSeats = findAllSeats(id);
		List<Seat> reservedSeats = findReservedSeats(id);
		
		for(int i = 0; i<reservedSeats.size(); i++) {
			if(allSeats.contains(reservedSeats.get(i)))
				allSeats.remove(reservedSeats.get(i));
		}
		return allSeats;
		}
	public Projection findOne(Long id){
		return projectionRepository.getOne(id);
	}
		
	}


