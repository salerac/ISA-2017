package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isa.domain.Requisite;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisiteRepository extends JpaRepository<Requisite, Long> {

}
