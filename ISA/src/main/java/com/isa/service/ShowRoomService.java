package com.isa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Seat;
import com.isa.domain.SeatType;
import com.isa.domain.ShowRoom;
import com.isa.repository.SeatRepository;
import com.isa.repository.ShowRoomRepository;

@Service
public class ShowRoomService {
	
	@Autowired
	private ShowRoomRepository showRoomRepository;
	@Autowired
	private SeatRepository seatRepository;
	
	
	public void makeSeats(Long id) {
		
		int k = 1;
		ArrayList<Seat> seats = new ArrayList<Seat>();
		ShowRoom sala = showRoomRepository.getOne(id);
		if(sala.getSeats().isEmpty() == true) {
		for(int i=0; i<sala.getWidth(); i++) {
			for(int j = 0; j<sala.getLength(); j++) {
				Seat seat = new Seat();
				seat.setType(SeatType.REGULAR);
				seat.setNumber(k);
				seats.add(seat);
				
				seatRepository.save(seat);
				k++;
			}
		}
		sala.setSeats(seats);
		showRoomRepository.save(sala);
		}
	}

}
