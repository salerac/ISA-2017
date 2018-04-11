package com.isa.service;

import java.util.Collection;

import com.isa.domain.User;



public interface UserService {
	Collection<User> findAll();

	User findOne(Long id);

	User save(User user) throws Exception;

	void delete(Long id);
	
	User findByEmail(String email); 
	
	User findByConfirmationToken(String confirmationToken); 
		
	
}
