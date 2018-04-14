package com.isa.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.domain.User;
import com.isa.repository.UserRepository;


@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public Collection<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findOne(Long id) {
		
		return repository.getOne(id);
	}

	@Override
	public User save(User user) throws Exception {
		
		return repository.save(user);
	}

	

	@Override
	public void delete(Long id) {
		User user = repository.getOne(id);
		if(user == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		repository.delete(user);
		
	}
	
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public User findByConfirmationToken(String confirmationToken) {
		return repository.findByConfirmationToken(confirmationToken);
	}
	

	@Override
	public void setActiveUser(User user) {
		final Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	}

	@Override
	public User getActiveUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
