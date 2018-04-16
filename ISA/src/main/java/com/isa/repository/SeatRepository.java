package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isa.domain.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
