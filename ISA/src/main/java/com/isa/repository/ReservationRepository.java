package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.domain.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	@Query
	List<Reservation> findByReserverId(long Id);
	@Query
	List<Reservation> findByProjectionId(long Id);

}
