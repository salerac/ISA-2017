package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isa.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	@Query
	User findByConfirmationToken(String confirmationToken);
}
