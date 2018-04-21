package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.isa.domain.UserAd;

@Repository
public interface UserAdRepository extends JpaRepository<UserAd, Long> {
	@Query
	List<UserAd> findByCreatorId(long Id);

}
